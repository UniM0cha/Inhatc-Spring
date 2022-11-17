package kr.inhatc.spring.solstice_shop.order.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.solstice_shop.item.constant.ItemSellStatus;
import kr.inhatc.spring.solstice_shop.item.entity.Item;
import kr.inhatc.spring.solstice_shop.item.repository.ItemRepository;
import kr.inhatc.spring.solstice_shop.member.entity.Member;
import kr.inhatc.spring.solstice_shop.member.repository.MemberRepository;
import kr.inhatc.spring.solstice_shop.order.repository.OrderItemRepository;
import kr.inhatc.spring.solstice_shop.order.repository.OrderRepository;

@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemNm("아이폰 12");
        item.setPrice(1290000);
        item.setItemDetail("아주 좋은 폰이다.");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascatdTest() {
        Order order = new Order();
        for (int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1290000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        // orderItem repository에서 저장한게 아니라
        // order repository에서 저장했는데도
        // 영속성 전이 설정으로 인해 저장이 된다.
        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

    public Order createOreder() {
        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            // 아이템 저장
            Item item = createItem();
            itemRepository.save(item);

            // orderItem 생성
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);

            // order쪽에서 orderItems 저장
            order.getOrderItems().add(orderItem);
        }

        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = this.createOreder();
        // order쪽에서 orderItem을 삭제함.
        // 부모 엔티티와 연관관계가 끊어졌기 때문에 orderItem도 지워진다.
        order.getOrderItems().remove(0);
        em.flush();
    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lasyLoadingTest() {
        Order order = this.createOreder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
        System.out.println("Order class : " + orderItem.getOrder().getClass());
    }

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "이정윤", roles = "USER")
    public void auditingTest() {
        Member newMember = new Member();
        memberRepository.save(newMember);

        em.flush();
        em.clear();

        Member member = memberRepository.findById(newMember.getId()).orElseThrow(EntityNotFoundException::new);

        System.out.println("register time : " + member.getRegTime());
        System.out.println("update time : " + member.getUpdateTime());
        System.out.println("create time : " + member.getCreatedBy());
        System.out.println("modify time : " + member.getModifiedBy());
    }
}