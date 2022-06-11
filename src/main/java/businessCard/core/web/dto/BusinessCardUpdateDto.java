package businessCard.core.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BusinessCardUpdateDto {

    private String name;
    private String company;
    private String role;
    private String tel;
    private String email;


}
