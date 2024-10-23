package com.lyj.proj.springbatch_app_10.app.member.controller;

import com.lyj.proj.springbatch_app_10.app.member.form.JoinForm;
import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
import com.lyj.proj.springbatch_app_10.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PreAuthorize("isAnonymous()") // 인증 되지 않은 놈만 실행 가능 == 로그인 안한 놈만 접근 가능
    @GetMapping("/login")
    public String showLogin(HttpServletRequest request) {
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/member/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        return "member/login";
    }

    @PreAuthorize("isAnonymous()") // 인증 되지 않은 놈만 실행 가능 == 로그인 안한 놈만 접근 가능
    @GetMapping("/join")
    public String showJoin() {
        return "member/join";
    }

    @PreAuthorize("isAnonymous()") // 인증 되지 않은 놈만 실행 가능 == 로그인 안한 놈만 접근 가능
    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm) { //@Valid == 유효성 검사. (똑바로 들어왔는지 검증해줌)
        memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getEmail());
        //회원가입 완료 되면 어디로 갈건지
        return "redirect:/member/login?msg=" + Ut.url.encode("회원가입이 완료되었습니다.");
    }
}
