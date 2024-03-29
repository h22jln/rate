package myapp.rate.repository;

import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;

import java.util.List;

public interface ChatRepository {
    public void saveRoomInfo(String roomId, ChatRoom room);
    public ChatMessage saveMessage(ChatMessage chatMessage);
    public List<ChatRoom> getRoomsInfo();
    public ChatRoom getRoomInfoByRoomId(String roomId);
    public List<ChatMessage> getAllMessages(String roomId);
    public int isalreadyIn(String roomId, String userId);
    String roomOut(String roomId, String userId);
    boolean isRoomCreater(String roomId, String userId);
    void roomDelete(String roomId);
}
