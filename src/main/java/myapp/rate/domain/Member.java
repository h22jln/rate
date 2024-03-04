package myapp.rate.domain;

import lombok.*;

@Data
public class Member {
    private String userId;
    private String userPw;
    private String userNickname;
    private String userName;
    private String role;
}
