package myapp.rate.repository;

import myapp.rate.domain.JoinForm;
import myapp.rate.domain.Member;
import myapp.rate.service.PasswordHashing;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate template;

    public MemberRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";

    @Override
    public int joinMember(JoinForm joinForm) {
        String sql = "insert into rate_member(user_id, user_pw, user_nickname, user_name, join_date, role) " +
                "values(?, ?, ?, ?, NOW(),?)";
        return template.update(sql,
                joinForm.getId(),
                PasswordHashing.hashPassword(joinForm.getPassword()),
                joinForm.getNickname(),
                joinForm.getName(),
                joinForm.getId().startsWith("admin") ? ROLE_ADMIN : ROLE_USER);
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

    @Override
    public Member findMember(String id) {
        String sql = "select * from rate_member where user_id = ?";
        try {
            Member member = template.queryForObject(sql, memberRowMapper(), id);
            return member;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setUserId(rs.getString("user_id"));
            member.setUserPw(rs.getString("user_pw"));
            member.setUserName(rs.getString("user_name"));
            member.setUserNickname(rs.getString("user_nickname"));
            member.setRole(rs.getString("role"));
            return member;

        };
    }

}
