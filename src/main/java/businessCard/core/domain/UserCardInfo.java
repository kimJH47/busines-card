package businessCard.core.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class UserCardInfo extends BaseTimeEntity{

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;

    public void changeUser(User user) {
        this.user = user;
    }

    public void changeCard(BusinessCard card){
        this.businessCard = card;
    }

}
