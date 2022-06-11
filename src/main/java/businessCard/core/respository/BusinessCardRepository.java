package businessCard.core.respository;

import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessCardRepository extends JpaRepository<BusinessCard,Long>,CustomBusinessCardRepository {

    List<BusinessCard> findByUser(User user);
}
