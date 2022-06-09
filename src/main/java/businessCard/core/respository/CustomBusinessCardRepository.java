package businessCard.core.respository;


import businessCard.core.domain.BusinessCard;

import java.util.List;

public interface CustomBusinessCardRepository {

    List<BusinessCard> findBusinessCards(BusinessCardSearch businessCardSearch);
}
