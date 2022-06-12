package businessCard.core.respository;


import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.User;

import java.util.List;

public interface CustomBusinessCardRepository {

    List<BusinessCard> findBusinessCards(BusinessCardSearch businessCardSearch, User user);
}
