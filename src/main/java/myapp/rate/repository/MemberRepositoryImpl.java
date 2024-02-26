package myapp.rate.repository;

import myapp.rate.domain.JoinForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate template;

    public MemberRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public int joinMember(JoinForm joinForm) {
        String sql = "insert into rate_member(user_id, user_pw, user_nickname, user_name, join_date) values(?, ?, ?, ?)";
        return template.update(sql,
                joinForm.getId(),
                joinForm.getPassword(),
                joinForm.getNickname(),
                joinForm.getName());
    }

    @Override
    public boolean idCheck(String id) {
        String sql = "select count(*) from rate_member where user_id = ?";
        return template.queryForObject(sql, Integer.class, id) < 1 ? true : false;
    }

    @Override
    public boolean nicknameCheck(String nickName) {
        String sql = "select count(*) from rate_member where user_nickname = ?";
        return template.queryForObject(sql, Integer.class, nickName) < 1 ? true : false;
    }

}
