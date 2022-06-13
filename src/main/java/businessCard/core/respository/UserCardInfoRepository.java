package businessCard.core.respository;

import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.User;
import businessCard.core.domain.UserCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCardInfoRepository extends JpaRepository<UserCardInfo, Long> {

    @Query("select uc from UserCardInfo uc join fetch uc.user where uc.user =:user")
    List<UserCardInfo> findByUser(@Param("user") User user);


    @Query("select uc from UserCardInfo uc join fetch uc.businessCard where uc.businessCard =:businessCard")
    List<UserCardInfo> findByBusinessCard(@Param("businessCard")BusinessCard businessCard);

}
