package myapp.rate.controller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PageController {
    @GetMapping("/mypage")
    public String mypage(){
        return "mypage";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/500")
    public String throw500() {
        return "join";
    }
    @GetMapping("/404")
    public ResponseEntity<?> someRoute() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }
}
