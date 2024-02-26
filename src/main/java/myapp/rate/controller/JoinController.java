package myapp.rate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.JoinForm;
import myapp.rate.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@AllArgsConstructor
public class JoinController {
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("joinForm", new JoinForm());
        return "join";
    }
    @PostMapping("/join")
    public String joinProceed(@Validated @ModelAttribute("joinForm") JoinForm form,
                              BindingResult bindingResult){

        //비밀번호와 비밀번호 확인이 같지 않으면 오류
        if (form.getPassword() != null && form.getPasswordCheck() != null) {
            if (!form.getPassword().equals(form.getPasswordCheck())) {
                bindingResult.reject("passwordNotSame", null);
            }
        }

        // DB접근
        memberService.joinProceed(form,bindingResult);

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            return "join";
        }


        return "redirect:/";
    }
}
