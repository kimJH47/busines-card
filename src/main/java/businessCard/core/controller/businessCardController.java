package businessCard.core.controller;


import businessCard.core.respository.BusinessCardSearch;
import businessCard.core.service.BusinessCardService;
import businessCard.core.web.dto.BusinessCardRequest;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.webflow.view.AjaxThymeleafViewResolver;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class businessCardController {

    private final BusinessCardService businessCardService;


    //명함저장 Api, 유저ID는 Dto에 포함
    @PostMapping("/api/business-card")
    public Long save(@RequestBody BusinessCardSaveDto businessCardSaveDto, HttpSession httpSession) {
        Long userId =(Long) httpSession.getAttribute("userId");
        return businessCardService.save(businessCardSaveDto,userId);
    }

    @PutMapping("/api/business-card/{id}")
    public Long update(@PathVariable Long id, @RequestBody BusinessCardUpdateDto requestDto) {
        return businessCardService.update(id, requestDto);
    }

    @DeleteMapping("/api/business-card/{id}")
    public Long delete(@PathVariable Long id) {
        businessCardService.delete(id);
        return id;
    }

    @PostMapping(value = "/api/business-card/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCards(@RequestBody BusinessCardSearch businessCardSearch) {
        List<BusinessCardRequest> businessCards = businessCardService.findBusinessCards(businessCardSearch);
        for (BusinessCardRequest businessCard : businessCards) {
            System.out.println("businessCard = " + businessCard.getId());
        }
        return ResponseEntity.ok().body(businessCards);
    }
}
