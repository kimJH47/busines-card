package businessCard.core.service;


import businessCard.core.domain.*;
import businessCard.core.respository.*;
import businessCard.core.web.dto.BusinessCardRequest;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final UserRepository userRepository;
    private final BusinessCardRepository businessCardRepository;
    private final CustomBusinessCardRepositoryImpl customBusinessCardRepository;


    @Transactional
    public Long save(BusinessCardSaveDto businessCardSaveDto, Long userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("테이블에 유저가 존재하지 않습니다"));
        BusinessCard businessCard = businessCardSaveDto.toEntity();
        businessCard.changeUser(user);
        return businessCardRepository.save(businessCard)
                                     .getId();

    }

    @Transactional
    public Long update(Long id, BusinessCardUpdateDto updateDto) {

        BusinessCard businessCard = businessCardRepository.findById(id)
                                                          .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다"));
        businessCard.update(updateDto);
        return businessCard.getId();
    }

    @Transactional
    public void delete(Long id) {
        BusinessCard businessCard = businessCardRepository.findById(id)
                                                          .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다"));
        businessCardRepository.delete(businessCard);

    }

    @Transactional(readOnly = true)
    public List<BusinessCardRequest> findAllDesc() {
        List<BusinessCard> cards = businessCardRepository.findAll();
        return cards.stream()
                    .map(businessCard -> BusinessCardRequest.builder()
                                                            .id(businessCard.getId())
                                                            .company(businessCard.getCompany())
                                                            .name(businessCard.getName())
                                                            .role(businessCard.getRole())
                                                            .tel(businessCard.getTel())
                                                            .email(businessCard.getEmail())
                                                            .image(businessCard.getImage())
                                                            .uploadTime(businessCard.getModifiedTime())
                                                            .build())
                    .collect(Collectors.toList());


    }
    @Transactional(readOnly = true)
    public List<BusinessCardRequest> findByUserIdAllDesc(Long id) {

        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("유저가 테이블에 존재하지 않습니다"));
        List<BusinessCard> cards = businessCardRepository.findByUser(user);
        return cards.stream()
                    .map(BusinessCardRequest::new)
                    .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<BusinessCardRequest> findBusinessCards(BusinessCardSearch businessCardSearch) {

        List<BusinessCard> businessCards = customBusinessCardRepository.findBusinessCards(businessCardSearch);
        return businessCards.stream()
                            .map(businessCard -> BusinessCardRequest.builder()
                                                                    .company(businessCard.getCompany())
                                                                    .name(businessCard.getName())
                                                                    .role(businessCard.getRole())
                                                                    .tel(businessCard.getTel())
                                                                    .email(businessCard.getEmail())
                                                                    .image(businessCard.getImage())
                                                                    .uploadTime(businessCard.getModifiedTime())
                                                                    .build())
                            .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public BusinessCardRequest findById(Long id) {
        return new BusinessCardRequest(businessCardRepository.findById(id)
                                                             .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다")));
    }

}

