package businessCard.core.service;


import businessCard.core.domain.*;
import businessCard.core.respository.BusinessCardRepository;
import businessCard.core.respository.BusinessCardSearch;
import businessCard.core.respository.UserRepository;
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

    public List<BusinessCardRequest> findAllDesc() {
        List<BusinessCard> cards = businessCardRepository.findAll();
        return cards.stream()
                    .map(businessCard -> BusinessCardRequest.builder()
                                                            .company(businessCard.getCompany())
                                                            .name(businessCard.getName())
                                                            .role(businessCard.getRole())
                                                            .tell(businessCard.getTell())
                                                            .email(businessCard.getEmail())
                                                            .image(businessCard.getImage())
                                                            .uploadTime(businessCard.getModifiedTime())
                                                            .build())
                    .collect(Collectors.toList());


    }


    public List<BusinessCardRequest> findBusinessCards(BusinessCardSearch businessCardSearch) {
        String content = businessCardSearch.getContent();
        String teg = businessCardSearch.getTeg();
        //QueryDSL

        return null;
    }


}

