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
public class ChatService {

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
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRepository.saveRoomInfo(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public void saveMessage(ChatMessage message){
        chatRepository.saveMessage(message);
    }
}