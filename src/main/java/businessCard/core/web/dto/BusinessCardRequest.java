package businessCard.core.web.dto;


import businessCard.core.domain.BusinessCard;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BusinessCardRequest {

    private Long id;
    @NotNull
    private Long userId;
    private String company;
    private String name;
    private String tell;
    private String role;
    private String email;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime uploadTime;
    private String image;


}
