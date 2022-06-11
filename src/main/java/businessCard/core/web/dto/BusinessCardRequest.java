package businessCard.core.web.dto;


import businessCard.core.domain.BusinessCard;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BusinessCardRequest {

    private Long id;
    private String company;
    private String name;
    private String tel;
    private String role;
    private String email;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime uploadTime;
    private String image;

    public BusinessCardRequest(BusinessCard businessCard) {
        this.id = businessCard.getId();
        this.company = businessCard.getCompany();
        this.name = businessCard.getName();
        this.tel = businessCard.getTel();
        this.role = businessCard.getRole();
        this.email = businessCard.getEmail();
        this.uploadTime = businessCard.getModifiedTime();
        this.image = businessCard.getImage();
    }

}
