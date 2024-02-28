package myapp.rate.domain;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Size(min = 4, max = 10)
    private String id;

    @AssertTrue
    private Boolean idValid;

    @NotBlank
    @Size(min = 1, max = 10)
    private String nickname;

    @AssertTrue
    private Boolean nicknameValid;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 8, max = 20)
    private String passwordCheck;

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;
}
