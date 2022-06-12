package businessCard.core.service;


import businessCard.core.domain.*;
import businessCard.core.respository.*;
import businessCard.core.web.dto.BusinessCardRequest;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import businessCard.core.web.dto.RegisteredDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final UserRepository userRepository;
    private final BusinessCardRepository businessCardRepository;
    private final CustomBusinessCardRepositoryImpl customBusinessCardRepository;
    private final UserCardInfoRepository userCardInfoRepository;

    @Transactional
    public Long save(BusinessCardSaveDto businessCardSaveDto, Long userId) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("테이블에 유저가 존재하지 않습니다"));
        BusinessCard businessCard = businessCardSaveDto.toEntity();
        businessCard.changeUser(user);
        BusinessCard card = businessCardRepository.save(businessCard);

        //유저가 가지는 카드정보들에 바로 추가
        return userCardInfoRepository.save(UserCardInfo.builder()
                                                       .businessCard(card)
                                                       .user(user)
                                                       .build()).getId();

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
        List<UserCardInfo> finds = userCardInfoRepository.findByUser(user);
        List<BusinessCardRequest> cardRequests = finds.stream()
                                                 .map(
                                                         userCardInfo -> new BusinessCardRequest(userCardInfo.getBusinessCard())
                                                 )
                                                 .collect(Collectors.toList());

        return cardRequests;

    }
    @Transactional(readOnly = true)
    public List<BusinessCardRequest> findBusinessCards(Long userId, BusinessCardSearch businessCardSearch) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("테이블에 유저가 없습니다"));

        List<BusinessCard> businessCards = customBusinessCardRepository.findBusinessCards(businessCardSearch,user);
        return businessCards.stream()
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
    public BusinessCardRequest findById(Long id) {
        return new BusinessCardRequest(businessCardRepository.findById(id)
                                                             .orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 없습니다")));
    }

    @Transactional
    public List<Long> registered(Long id,RegisteredDto registeredDto) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("테이블에 유저가 없습니다"));

        List<Optional<BusinessCard>> cards = registeredDto.getIds()
                                                          .stream()
                                                          .map(aLong -> businessCardRepository.findById(user.getId()))
                                                          .collect(Collectors.toList());
        return cards.stream()
                    .map(businessCard -> businessCard.orElseThrow(() -> new IllegalArgumentException("테이블에 명함이 존재하지않습니다")))
                    .map(businessCard -> userCardInfoRepository.save(UserCardInfo.builder()
                                                                                 .businessCard(businessCard)
                                                                                 .user(user)
                                                                                 .build()))
                    .map(userCardInfo -> userCardInfo.getId())
                    .collect(Collectors.toList());


    }
}