package businessCard.core.respository;

import businessCard.core.domain.BusinessCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessCardRepository extends JpaRepository<BusinessCard,Long>,CustomBusinessCardRepository {
}
