package businessCard.core.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RegisteredDto {

    private Long userId;
    private List<Long> ids;
}
