package myapp.rate.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import myapp.rate.service.MypageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@AllArgsConstructor
public class MypageController {
    private final MypageService mypageService;
    @GetMapping
    public String mypage(){
        return "mypage/mypage";
    }
    @GetMapping("/profile")
    public String profile(){
        return "mypage/profile";
    }
    @GetMapping("/list")
    public String list(Model model, HttpSession session){
        String userId = String.valueOf(session.getAttribute("userName"));
        model.addAttribute("commentList",mypageService.getContentByUserId(userId));
        return "mypage/list";
    }
    @GetMapping("/setting")
    public String setting(){
        return "mypage/setting";
    }
}
