package myapp.rate.controller;

import lombok.RequiredArgsConstructor;
import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.repository.ChatRepository;
import myapp.rate.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message,
                      @RequestParam String userId) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        chatService.saveMessage(message);
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}