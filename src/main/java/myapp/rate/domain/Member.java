package myapp.rate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private String userId;
    private String userPw;
    private String userNickname;
    private String userName;
}
