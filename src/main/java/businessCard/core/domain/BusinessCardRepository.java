package businessCard.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessCardRepository extends JpaRepository<BusinessCard,Long> {
}
