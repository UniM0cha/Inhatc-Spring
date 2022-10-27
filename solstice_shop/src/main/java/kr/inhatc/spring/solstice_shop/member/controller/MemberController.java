package kr.inhatc.spring.solstice_shop.member.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.spring.solstice_shop.member.dto.MemberFormDto;
import kr.inhatc.spring.solstice_shop.member.entity.Member;
import kr.inhatc.spring.solstice_shop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "member/memberLogin";
    }

    @GetMapping("/login/error")
    public String errorPage(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 패스워드가 잘못되었습니다.");
        return "member/memberLogin";
    }

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        /**
         * Valid : Dto 내에 적힌 어노테이션을 바탕으로 값 유효성 검사
         * BindingResult : 값이 제대로 들어갔는지 결과를 리턴하는 것
         */

        // 오류가 있을 경우 (유효성 검사에 통과하지 못한 경우)
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            log.info("==================>" + memberFormDto.toString());
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            log.error(e);
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    /**
     * 스프링 시큐리티의 조건
     * 1. username이 있어야 한다.
     * 2. password가 있어야 한다.
     * 3. role이 있어야 한다.
     */
}
