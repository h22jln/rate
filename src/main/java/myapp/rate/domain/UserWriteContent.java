package myapp.rate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWriteContent {
    private String address;
    private String comment;
    private Date regDt;
    private int idx;
}
