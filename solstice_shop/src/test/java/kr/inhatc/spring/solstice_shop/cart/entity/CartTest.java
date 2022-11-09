package kr.inhatc.spring.solstice_shop.cart.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.solstice_shop.cart.repository.CartRepository;
import kr.inhatc.spring.solstice_shop.member.dto.MemberFormDto;
import kr.inhatc.spring.solstice_shop.member.entity.Member;
import kr.inhatc.spring.solstice_shop.member.repository.MemberRepository;

@SpringBootTest
@Transactional
public class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    /**
     * 임시 멤버 생성 함수
     */
    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("solst_ice@naver.com");
        memberFormDto.setName("이정윤");
        memberFormDto.setAddress("인천시 남동구 도림동");
        memberFormDto.setPassword("wjddbs1025");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        // 멤버 생성 & 저장
        Member member = createMember();
        memberRepository.save(member);

        // 장바구니 생성
        Cart cart = new Cart();
        // 장바구니에 멤버 등록
        cart.setMember(member);
        // 장바구니 저장
        cartRepository.save(cart);

        // 적용
        em.flush();
        em.clear();

        // 저장된 장바구니를 불러온다. 만약 없으면 EntityNotFoundException을 던진다
        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);

        // 장바구니에 저장된 member의 id와 위에서 저장했던 member의 id가 같은지 확인한다.
        assertEquals(savedCart.getMember().getId(), member.getId());

    }

}
