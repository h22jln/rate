package myapp.rate.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;
import myapp.rate.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String roomName, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String createUserName = String.valueOf(session.getAttribute("userName"));
        return chatService.createRoom(roomName,createUserName);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId,
                             HttpServletRequest request) {
        List<ChatMessage> messages = chatService.getAllMessages(roomId);
        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        model.addAttribute("roomId", roomId);
        model.addAttribute("messages", messages);
        return "/chat/roomdetail";
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }

    // 이미 들어와있는지 여부 확인
    @PostMapping("/room/isAlreadyIn")
    @ResponseBody
    public int isAlreadyIn(@RequestBody Map<String, String> data) {
        return chatService.isalreadyIn(data.get("roomId"),data.get("sender"));
    }

    // 방 나가기
    @PostMapping("/room/out")
    @ResponseBody
    public String roomOut(@RequestBody Map<String, String> data) {
        return chatService.roomOut(data.get("roomId"),data.get("sender"));
    }
}
