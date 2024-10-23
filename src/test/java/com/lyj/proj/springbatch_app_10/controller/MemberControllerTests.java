package com.lyj.proj.springbatch_app_10.controller;

import com.lyj.proj.springbatch_app_10.app.member.controller.MemberController;
import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // MockMVC 설정 / MockMVC == 실제 웹서버를 띄우지 않아도 HTTP요청을 흉내내서 컨트롤러 메서드를 실행
@Transactional // 테스트 메서드가 끝나면 다시 롤백
@ActiveProfiles("test")
public class MemberControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 폼")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/member/join")) // member/join GET 요청 -> 폼 내놔
                .andDo(print());
        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful()) // 성공(200번대)했는지?
                .andExpect(handler().handlerType(MemberController.class)) // 요청을 MemberController가 처리했는지?
                .andExpect(handler().methodName("showJoin")) // 실행된 method가 showJoin인지?
                .andExpect(content().string(containsString("회원가입"))); // 해당 페이지에 "회원가입" 텍스트가 있는지?
    }

    @Test
    @DisplayName("회원가입")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/member/join") // member/join POST 요청 해
                        .with(csrf()) // csrf 토큰을 추가해서 보안 검증 통과
                        .param("username", "user999")
                        .param("password", "1234")
                        .param("email", "user999@test.com")
                )
                .andDo(print());
        // THEN
        resultActions
                .andExpect(status().is3xxRedirection()) // 리다이렉션(300번대)이 되었는지?
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(redirectedUrlPattern("/member/login?msg=**")); // 리다이렉트 URL 패턴이 "/member/login?msg=**" 형식인지?
        assertThat(memberService.findByUsername("user999").isPresent()).isTrue();
    }
}


//import com.lyj.proj.springbatch_app_10.app.member.controller.MemberController;
//import com.lyj.proj.springbatch_app_10.app.member.service.MemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////TDD (테스트 주도 방식 개발)
//
//@SpringBootTest
////MocMvc 설정 / MocMvd => 실제 웹서버를 띄우지 않아도 HTTP요청을 흉내내서 Controller 메서드를 실행시켜줌
////서버를 구동하지 않아도 잠깐 요청 보내는 척을 해주는 거임
////Get이나 Post 요청을 이 테스트 환경에서 보낼 수 있다는 것
//@AutoConfigureMockMvc
//@Transactional //테스트 메서드가 끝나면 다시 롤백
//@ActiveProfiles("test")
//public class MemberControllerTests {
//    @Autowired
//    private MockMvc mvc;
//    @Autowired
//    private MemberService memberService;
//
//    @Test
//    @DisplayName("회원가입 폼")
//    void t1() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(get("/member/join")) //member/join에 get요청을 보냄 == form 내놔
//                .andDo(print());
//        // THEN
//        resultActions
//                .andExpect(status().is2xxSuccessful()) //200번대 == 성공, 성공으로 끝났는지 실패로 끝났는지?
//                .andExpect(handler().handlerType(MemberController.class)) //요청을 MemberContorller가 처리했는지?
//                .andExpect(handler().methodName("showJoin")) //실행된 Method가 showJoin인지?
//                .andExpect(content().string(containsString("회원가입"))); //해당 페이지에 "회원가입" 텍스트가 있는지?
//        //"Expect"이므로 한국어로 풀면 의문문이다.
//    }
//
//    @Test
//    @DisplayName("회원가입")
//    void t2() throws Exception {
//        // WHEN
//        ResultActions resultActions = mvc
//                .perform(post("/member/join") // member/join에 Post 요청을 보냄
//                        .with(csrf()) //csrf 토큰을 추가해서 보안 검증 통과를 하도록 함
//                        .param("username", "user999") //파라미터로 이것들을 넘기겠다(아래 3줄), 회원가입 데이터
//                        .param("password", "1234")
//                        .param("email", "user999@test.com")
//                )
//                .andDo(print());
//        // THEN
//        resultActions
//                .andExpect(status().is3xxRedirection()) //Redirection이 되었는지? (300번대)
//                .andExpect(handler().handlerType(MemberController.class))
//                .andExpect(handler().methodName("join"))
//                .andExpect(redirectedUrlPattern("/member/login?msg=**")); //리다이렉트 Url 패턴이 이 형식인지?
//        assertThat(memberService.findByUsername("user999").isPresent()).isTrue(); //"memberService에서 user999를 찾았을 때 존재하니?"가 True인지
//    }
//}