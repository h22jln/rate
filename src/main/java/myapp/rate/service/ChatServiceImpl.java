package myapp.rate.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;
import myapp.rate.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private Map<String, ChatRoom> chatRooms;
    private final ChatRepository chatRepository;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
//        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        return chatRepository.getRoomsInfo();
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRepository.getRoomInfoByRoomId(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String roomName, String createUserId) {
        ChatRoom chatRoom = ChatRoom.create(roomName);
        chatRoom.setCreateUserId(createUserId);
        chatRepository.saveRoomInfo(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public ChatMessage saveMessage(ChatMessage message){
        return chatRepository.saveMessage(message);
    }

    @Override
    public List<ChatMessage> getAllMessages(String roomId) {
        return chatRepository.getAllMessages(roomId);
    }

    @Override
    public int isalreadyIn(String roomId, String userId) {
        return chatRepository.isalreadyIn(roomId,userId);
    }

    @Override
    public String roomOut(String roomId, String userId) {
        return chatRepository.roomOut(roomId,userId);
    }

    @Override
    public boolean isRoomCreater(String roomId, String userId) {
        return chatRepository.isRoomCreater(roomId, userId);
    }

    @Override
    public void roomDelete(String roomId) {
        chatRepository.roomDelete(roomId);
    }
}