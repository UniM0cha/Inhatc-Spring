package kr.inhatc.spring.solstice_shop.item.repository;

import static kr.inhatc.spring.solstice_shop.item.entity.QItem.item;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inhatc.spring.solstice_shop.item.constant.ItemSellStatus;
import kr.inhatc.spring.solstice_shop.item.entity.Item;
import kr.inhatc.spring.solstice_shop.item.entity.QItem;

@SpringBootTest
public class ItemRepositoryTest {

  // 옛날에는 PersistenceContext 어노테이션을 붙혀줬는데 이젠 Autowired로 통합됨
  // @PersistenceContext
  @Autowired
  EntityManager em;

  @Autowired
  ItemRepository itemRepository;

  @Test
  void test() {
    Item item = new Item();
    System.out.println(item);
  }

  @Test
  @DisplayName("상품 저장 테스트")
  public void createItemTest() {
    Item item = new Item();
    item.setItemNm("테스트 상품");
    item.setPrice(10000);
    item.setItemDetail("테스트 상품 상세 설명");
    item.setItemSellStatus(ItemSellStatus.SELL);
    item.setStockNumber(100);
    Item savedItem = itemRepository.save(item);
    System.out.println(savedItem.toString());
  }

  public void createItemList() {
    for (int i = 1; i <= 10; i++) {
      Item item = new Item();
      item.setItemNm("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SELL);
      item.setStockNumber(100);
      item.setRegTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      Item savedItem = itemRepository.save(item);
    }
  }

  @Test
  @DisplayName("상품명 조회 테스트")
  public void findByItemNmTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }

  @Test
  @DisplayName("상품명, 상품상세설명 or 테스트")
  public void findByItemNmOrItemDetailTest() {
    this.createItemList();
    List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }
  }

  @Test
  @DisplayName("JPQL 쿼리")
  public void findByItemDetailTest() {
    this.createItemList();
    List<Item> items = itemRepository.findByItemDetail("테스트");
    for (Item item : items) {
      System.out.println(item.toString());
    }
  }

  @Test
  @DisplayName("Native 쿼리")
  public void findByItemDetailNativeTest() {
    this.createItemList();
    List<Item> items = itemRepository.findByItemDetailNative("테스트");
    for (Item item : items) {
      System.out.println(item.toString());
    }
  }

  @Test
  @DisplayName("QueryDSL test")
  public void querydslTest() {
    this.createItemList();

    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
    // QItem qItem = QItem.item;
    // QItem qItem = new QItem("i");

    // List<Item> items = jpaQueryFactory
    // .select(qItem)
    // .from(qItem)
    // .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
    // .where(qItem.itemDetail.like("%1%"))
    // .orderBy(qItem.price.desc())
    // .fetch();

    List<Item> items = jpaQueryFactory
        .selectFrom(item)
        // .select(item)
        // .from(item)
        .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
        .where(item.itemDetail.like("%1%"))
        .orderBy(item.price.desc())
        .fetch();

    for (Item item : items) {
      System.out.println(item);
    }
  }

  public void createItemList2() {
    for (int i = 1; i <= 5; i++) {
      Item item = new Item();
      item.setItemNm("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SELL);
      item.setStockNumber(100);
      item.setRegTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      itemRepository.save(item);
    }

    for (int i = 6; i <= 10; i++) {
      Item item = new Item();
      item.setItemNm("테스트 상품" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("테스트 상품 상세 설명" + i);
      item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
      item.setStockNumber(0);
      item.setRegTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      itemRepository.save(item);
    }
  }

  @Test
  @DisplayName("querydsl 테스트2")
  public void querydlsTest2() {
    createItemList2();

    String itemDetail = "테스트";
    int price = 10003;
    String itemSellState = "SELL";

    QItem item = QItem.item;

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    booleanBuilder
        .and(item.itemDetail.like("%" + itemDetail + "%"))
        .and(item.price.gt(price));

    if (StringUtils.equals(itemSellState, ItemSellStatus.SELL)) {
      booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
    }

    // page : 시작할 위치, size : 가져올 양
    Pageable pageable = PageRequest.of(1, 5);
    Page<Item> items = itemRepository.findAll(booleanBuilder, pageable);

    System.out.println("전체 갯수 : " + items.getTotalElements());

    List<Item> items2 = items.getContent();

    for (Item item2 : items2) {
      System.out.println(item2);
    }
  }

}
