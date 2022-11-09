package kr.inhatc.spring.solstice_shop.member.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.solstice_shop.member.dto.MemberFormDto;
import kr.inhatc.spring.solstice_shop.member.entity.Member;
import kr.inhatc.spring.solstice_shop.member.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 테스트에 사용할 임시 멤버 생성
     */
    public Member createMember(String email, String password) {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("이정윤");
        memberFormDto.setAddress("인천시 남동구 도림동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        String email = "solst_ice@naver.com";
        String password = "wjddbs1025";
        this.createMember(email, password);

        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin()
                        // username의 파라메터 이름을 email로 설정
                        .userParameter("email")
                        // /member/login의 url로 email과 password를 붙혀서 로그인 수행
                        .loginProcessingUrl("/member/login").user(email).password(password))
                // security의 인증된 상태를 예상한다
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception {
        String email = "solst_ice@naver.com";
        String password = "wjddbs1025";
        this.createMember(email, password);

        mockMvc.perform(
                SecurityMockMvcRequestBuilders.formLogin()
                        .userParameter("email")
                        // 일부러 비밀번호를 다르게 입력하여 테스트한다.
                        .loginProcessingUrl("/member/login").user(email).password("wjddbs"))
                // 인증되지 않은 상태를 예상한다
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}
