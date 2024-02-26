package myapp.rate.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinForm {
    @NotNull
    @Size(min = 4, max = 10, message = "4자 이상, 20자 이하를 허용합니다")
    private String id;
    @NotNull
    @Size(max = 10, message = "10자 이하를 허용합니다")
    private String nickname;
    @NotNull
    @Size(min = 8, max = 20, message = "8자 이상, 20자 이하를 허용합니다")
    private String password;
    @NotNull
    @Size(min = 8, max = 20, message = "8자 이상, 20자 이하를 허용합니다")
    private String passwordCheck;
    @NotNull
    @Size(max = 10, message = "10자 이하를 허용합니다")
    private String name;
}
