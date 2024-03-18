package myapp.rate.repository;

import lombok.SneakyThrows;
import myapp.rate.domain.chat.ChatMessage;
import myapp.rate.domain.chat.ChatRoom;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class ChatRepositoryImpl implements ChatRepository{
    private final JdbcTemplate template;

    public ChatRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public void saveRoomInfo(String roomId, ChatRoom room) {
        String sql = "insert into rate_chatting_rooms(room_id, room_name, create_user, regdt) " +
                "values(?, ?, ?, NOW())";
        template.update(sql, roomId, room.getRoomName(),room.getCreateUserId());
    }
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        LocalDateTime now = LocalDateTime.now();
        String sql = "insert into rate_chatting_message(room_id, sender, message, type, regdt) " +
                "values(?, ?, ?, ?, ?)";
        template.update(sql, chatMessage.getRoomId(),
                chatMessage.getSender(),chatMessage.getMessage(), chatMessage.getType().toString(),now);
        chatMessage.setRegDt(now);
        String formattedDateTime = convertFormattedDateTime(now);
        chatMessage.setFormattedDateTime(formattedDateTime);
        return chatMessage;
    }

    public List<ChatRoom> getRoomsInfo() {
        String sql = "select room_id, room_name " +
                "from rate_chatting_rooms " +
                "where delete_yn <> 'y'";
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

    public List<ChatMessage> getAllMessages(String roomId) {
        String sql = "select room_id, message, sender, regdt, type " +
                "from rate_chatting_message " +
                "where room_id = ?" +
                "order by regdt desc" ;

        try {
            return template.query(sql, messageRowMapper(),roomId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int isalreadyIn(String roomId, String userId) {
        String sql = "select count(*) " +
                      " from rate_chatting_message " +
                      "where sender = ? " +
                        "and room_id = ? ";
        int result = template.queryForObject(sql, Integer.class, userId, roomId);
        return result;
    }

    @Override
    public String roomOut(String roomId, String userId) {
        String sql = "delete from rate_chatting_message where sender = ? and room_id = ?";
        int update = template.update(sql, userId, roomId);
        // TODO: 에러코드 정의
        return null;
    }

    @Override
    public boolean isRoomCreater(String roomId, String userId) {
        String sql = "SELECT CASE WHEN EXISTS (" +
                "    SELECT 1" +
                "    FROM rate_chatting_rooms" +
                "    WHERE room_id = ?" +
                "    AND create_user = ?" +
                ") THEN 1 ELSE 0 END AS result";
        Integer result = template.queryForObject(sql, Integer.class, roomId, userId);
        return result == 1 ? true : false;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void roomDelete(String roomId) {
        String sql = "update rate_chatting_rooms set delete_yn = 'y' where room_id = ?";
        int update = template.update(sql, roomId);
        if (update > 1){
            throw new Exception();
        }
    }


    private RowMapper<ChatMessage> messageRowMapper() {
        return (rs, rowNum) -> {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setRoomId(rs.getString("room_id"));
            chatMessage.setSender(rs.getString("sender"));
            chatMessage.setMessage(rs.getString("message"));
            chatMessage.setType(ChatMessage.MessageType.valueOf(rs.getString("type")));

            // 오늘이면 시간만 보여주고, 아니면 날짜도 보여준다
            LocalDateTime dateTime = rs.getTimestamp("regdt").toLocalDateTime();
            String formattedDateTime = convertFormattedDateTime(dateTime);
            chatMessage.setFormattedDateTime(formattedDateTime);

            return chatMessage;
        };
    }

    private String convertFormattedDateTime(LocalDateTime dateTime){
        boolean isNotToday = !dateTime.toLocalDate().equals(LocalDate.now());

        String pattern = isNotToday? "yyyy-MM-dd a hh:mm" : "a hh:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
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
