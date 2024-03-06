package myapp.rate.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.MapContent;
import myapp.rate.domain.WriteForm;
import myapp.rate.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/write")
    public String board(Model model){
        model.addAttribute("writeForm", new WriteForm());
        return "board/write";
    }
    @PostMapping("/write")
    public String write(@Validated @ModelAttribute("writeForm") WriteForm form,
                        BindingResult bindingResult,
                        HttpSession session){

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}",bindingResult);
            return "/board/write";
        }

        // 사용자 아이디
        form.setUserId((String)session.getAttribute("userName"));

        // 위도 경도
        Map<String, String> cordinates = boardService.getCordinate(form.getAddress());
        form.setLatitude(cordinates.get("latitude"));
        form.setLongitude(cordinates.get("longitude"));

        int result = boardService.contentSave(form);
        if(result != 1){
            return "/board/write";
        }
        return "redirect:/map";
    }
    @GetMapping("/map")
    public String map(Model model){
        List<MapContent> mapContents = boardService.getMapContents();
        model.addAttribute("mapContents", mapContents);
        return "board/map";
    }
    @GetMapping("/content")
    public String map(@RequestParam int idx, Model model){
        MapContent mapContent = boardService.getMapContent(idx);
        model.addAttribute("mapContent", mapContent);
        return "board/contentView";
    }
}
