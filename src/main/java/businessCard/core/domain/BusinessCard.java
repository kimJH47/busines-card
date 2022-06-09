package businessCard.core.domain;


import businessCard.core.web.dto.BusinessCardUpdateDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class BusinessCard extends BaseTimeEntity {


    @Id
    @GeneratedValue
    @Column(name = "business_card_id")
    private Long id;
    private String name;
    private String tell;
    private String role;
    private String company;
    private String email;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void changeUser(User user) {
        this.user = user;
    }

    public void update(BusinessCardUpdateDto businessCardUpdateDto) {
        this.name = businessCardUpdateDto.getName();
        this.tell = businessCardUpdateDto.getTell();
        this.role = businessCardUpdateDto.getRole();
        this.company = businessCardUpdateDto.getCompany();
        this.email = businessCardUpdateDto.getEmail();

    }
}
