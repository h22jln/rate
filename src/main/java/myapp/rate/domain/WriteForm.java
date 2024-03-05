package myapp.rate.domain;

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
public class WriteForm {
    @NotBlank(message = "주소 검색을 해주세요")
    private String address;
    @NotBlank
    @Size(max = 300)
    private String content;
    private String userId;
    private String latitude;
    private String longitude;
}
