package myapp.rate.repository;

import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class ChatRepository {
    private final JdbcTemplate template;

    public ChatRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public void saveRoomInfo(String roomId, ChatRoom room) {
        String sql = "insert into rate_chatting_rooms(room_id, room_name, regdt) " +
                "values(?, ?, NOW())";
        template.update(sql, roomId, room.getRoomName());
    }
    public void saveMessage(ChatMessage chatMessage) {
        String sql = "insert into rate_chatting_message(room_id, sender, message, regdt) " +
                "values(?, ?, ?, NOW())";
        template.update(sql, chatMessage.getRoomId(), chatMessage.getSender(),chatMessage.getMessage());
    }

    public List<ChatRoom> getRoomsInfo() {
        String sql = "select room_id, room_name " +
                "from rate_chatting_rooms ";
        try {
            return template.query(sql, roomsInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public ChatRoom getRoomInfoByRoomId(String roomId) {
        String sql = "select room_id, room_name " +
                "from rate_chatting_rooms " +
                "where room_id = ?";

        try {
            return template.queryForObject(sql, roomsInfoRowMapper(),roomId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private RowMapper<ChatMessage> messageRowMapper() {
        return (rs, rowNum) -> {
            ChatMessage chatMessage = new ChatMessage();
            return chatMessage;
        };
    }


    private RowMapper<ChatRoom> roomsInfoRowMapper() {
        return (rs, rowNum) -> {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomId(rs.getString("room_id"));
            chatRoom.setRoomName(rs.getString("room_name"));
            return chatRoom;
        };
    }


}
