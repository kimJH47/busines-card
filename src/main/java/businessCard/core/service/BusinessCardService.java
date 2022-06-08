package businessCard.core.service;


import businessCard.core.domain.BusinessCard;
import businessCard.core.domain.BusinessCardRepository;
import businessCard.core.domain.User;
import businessCard.core.domain.UserRepository;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final UserRepository userRepository;
    private final BusinessCardRepository businessCardRepository;


    @Transactional
    public Long save(BusinessCardSaveDto businessCardSaveDto) {
        User user = userRepository.findById(businessCardSaveDto.getUserId())
                                  .orElseThrow(() -> new IllegalArgumentException("테이블에 유저가 존재하지 않습니다"));
        BusinessCard businessCard = businessCardSaveDto.toEntity();
        businessCard.changeUser(user);
        return businessCardRepository.save(businessCard).getId();

    }

    @Transactional
    public void update(Long id, BusinessCardUpdateDto updateDto) {

        BusinessCard businessCard = businessCardRepository.findById(updateDto.getId())
                                                           .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다"));
        businessCard.update(updateDto);
    }

    @Transactional
    public void delete(Long id) {
        BusinessCard businessCard = businessCardRepository.findById(id)
                                                          .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다"));
        businessCardRepository.delete(businessCard);

    }
}
