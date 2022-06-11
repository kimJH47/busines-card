package businessCard.core.web.dto;


import businessCard.core.domain.BusinessCard;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BusinessCardSaveDto {

    private String company;
    private String name;
    private String tell;
    private String role;
    private String email;


    public BusinessCard toEntity() {
        return BusinessCard.builder()
                           .company(this.getCompany())
                           .email(this.getEmail())
                           .name(this.getName())
                           .role(this.getRole())
                           .tel(this.getTell())
                           .build();
    }
}
