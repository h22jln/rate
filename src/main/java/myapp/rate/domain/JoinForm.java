package myapp.rate.domain;

import jakarta.validation.constraints.AssertTrue;
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
    @Size(min = 4, max = 10)
    private String id;

    @NotNull
    @AssertTrue
    private Boolean idValid;

    @NotNull
    @Size(min = 1, max = 10)
    private String nickname;

    @NotNull
    @AssertTrue
    private Boolean nicknameValid;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @NotNull
    @Size(min = 8, max = 20)
    private String passwordCheck;

    @NotNull
    @Size(min = 1, max = 10)
    private String name;
}
