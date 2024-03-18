package myapp.rate.service;

import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ChatService {
    //채팅방 불러오기
    public List<ChatRoom> findAllRoom();

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId);

    //채팅방 생성
    public ChatRoom createRoom(String roomName, String createUserName);

    public ChatMessage saveMessage(ChatMessage message);

    public List<ChatMessage> getAllMessages(String roomId);

    public int isalreadyIn(String roomId, String userId);

    public String roomOut(String roomId, String userId);

    public boolean isRoomCreater(String roomId, String userId);

    public void roomDelete(String roomId);
}
