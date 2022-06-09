package businessCard.core.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserRequest {
    private String email;
    private Long id;
    private String name;
    private String picture;

}
