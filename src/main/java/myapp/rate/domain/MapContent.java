package myapp.rate.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapContent {
    private int idx;
    private String userNickname;
    private String comment;
    private String userId;
    private String latitude;
    private String longitude;
    private String address;
}
