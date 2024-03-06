package myapp.rate.repository;

import myapp.rate.domain.MapContent;
import myapp.rate.domain.WriteForm;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final JdbcTemplate template;

    public BoardRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public int contentSave(WriteForm writeForm) {
        String sql = "insert into rate_map_write(user_id, map_latitude, " +
                "map_longitude, comment, address, regdt) " +
                "values(?, ?, ?, ?, ?, NOW())";
        return template.update(sql,
                writeForm.getUserId()
                ,writeForm.getLatitude()
                ,writeForm.getLongitude()
                ,writeForm.getContent()
                ,writeForm.getAddress()
        );
    }

    @Override
    public List<MapContent> getMapContents() {
        String sql = "select w.idx, user_nickname, map_latitude, map_longitude, comment " +
                "from rate_map_write w " +
                "inner join rate_member m on w.user_id = m.user_id";
        try {
            return template.query(sql, MapContentsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public MapContent getMapContent(int idx) {
        String sql = "select user_nickname, comment, address " +
                "from rate_map_write w " +
                "inner join rate_member m on w.user_id = m.user_id  " +
                "where w.idx = ?";
        try {
            return template.queryForObject(sql, MapContentRowMapper(), idx);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<MapContent> MapContentRowMapper() {
        return (rs, rowNum) -> {
            MapContent mapContent = new MapContent();
            mapContent.setUserNickname(rs.getString("user_nickname"));
            mapContent.setComment(rs.getString("comment"));
            mapContent.setAddress(rs.getString("address"));
            return mapContent;
        };
    }

    private RowMapper<MapContent> MapContentsRowMapper() {
        return (rs, rowNum) -> {
            MapContent mapContent = new MapContent();
            mapContent.setIdx(rs.getInt("idx"));
            mapContent.setUserNickname(rs.getString("user_nickname"));
            mapContent.setLatitude(rs.getString("map_latitude"));
            mapContent.setLongitude(rs.getString("map_longitude"));
            mapContent.setComment(rs.getString("comment"));
            return mapContent;
        };
    }
}
