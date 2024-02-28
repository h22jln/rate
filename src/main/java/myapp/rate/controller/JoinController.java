package myapp.rate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.JoinForm;
import myapp.rate.service.MemberService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@AllArgsConstructor
public class JoinController {
    private final MemberService memberService;
    private final MessageSource messageSource;

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

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            return "join";
        }

        // DB접근
        memberService.joinProceed(form);


        return "redirect:/";
    }

    @GetMapping("/idValid")
    @ResponseBody
    public Map<String, String> idValid(@RequestParam String id){
        Map<String, String> result = new HashMap<>();
        boolean duplicate = memberService.idDuplicateCheck(id);
        boolean idLength = id.length() >= 4 && id.length() <= 10 ? true : false;

        String duplicateError = "";
        if(!duplicate){
            duplicateError = messageSource.getMessage("idDuplicate",null,null);
        }
        String idLengthError = "";
        if(!idLength){
            idLengthError = messageSource.getMessage("Size", new Object[]{null, 10,1}, null);
        }

        result.put("duplicate", duplicateError);
        result.put("length", idLengthError);

        return result;
    }

    @GetMapping("/nicknameValid")
    @ResponseBody
    public Map<String, String> nicknameValid(@RequestParam String nickname){
        Map<String, String> result = new HashMap<>();
        boolean duplicate = memberService.nicknameDuplicateCheck(nickname);
        boolean idLength = nickname.length() <= 10 ? true : false;

        String duplicateError = "";
        if(!duplicate){
            duplicateError = messageSource.getMessage("nicknameDuplicate",null,null);
        }
        String nicknameLengthError = "";
        if(!idLength){
            nicknameLengthError = messageSource.getMessage("Size", new Object[]{null, 10,1}, null);
        }

        result.put("duplicate", duplicateError);
        result.put("length", nicknameLengthError);

        return result;
    }
}
