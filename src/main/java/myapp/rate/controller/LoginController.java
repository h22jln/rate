package myapp.rate.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @PostMapping("/login")
//    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
//                          @RequestParam(defaultValue = "/") String redirectURL,
//                          HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
////        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
////        log.info("login? {}", loginMember);
////        if (loginMember == null) {
////            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
////            return "login/loginForm";
////        }
////
////        // 로그인 성공 처리
////        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
////        HttpSession session = request.getSession();
////        //세션에 로그인 회원 정보 보관
////        session.setAttribute(LOGIN_MEMBER,loginMember);
//
//        return "redirect:"+redirectURL;
//    }
}
