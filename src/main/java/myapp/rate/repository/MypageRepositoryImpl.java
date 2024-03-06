package myapp.rate.repository;

import myapp.rate.domain.MapContent;
import myapp.rate.domain.UserWriteContent;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MypageRepositoryImpl implements MypageRepository{

    private final JdbcTemplate template;
    public MypageRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserWriteContent> getContentByUserId(String userId) {
        String sql = "select address, comment, regdt, idx" +
                "from rate_map_write " +
                "where user_id = ?";
        try {
            return template.query(sql, UserWriteContentRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    private RowMapper<UserWriteContent> UserWriteContentRowMapper() {
        return (rs, rowNum) -> {
            UserWriteContent userWriteContent = new UserWriteContent();
            userWriteContent.setAddress(rs.getString("address"));
            userWriteContent.setComment(rs.getString("comment"));
            userWriteContent.setRegDt(rs.getDate("regdt"));
            userWriteContent.setIdx(rs.getInt("idx"));
            return userWriteContent;
        };
    }
}
