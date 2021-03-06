package businessCard.core.controller;


import businessCard.core.respository.BusinessCardSearch;
import businessCard.core.service.BusinessCardService;
import businessCard.core.web.dto.BusinessCardRequest;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import businessCard.core.web.dto.RegisteredDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/api/business-card/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCardByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                             .body(businessCardService.findByUserIdAllDesc(id));
    }
    @PostMapping(value = "/api/business-card/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCards(@RequestBody BusinessCardSearch businessCardSearch, HttpSession httpSession) {
        Long userId =(Long)httpSession.getAttribute("userId");
        List<BusinessCardRequest> businessCards = businessCardService.findBusinessCards(userId,businessCardSearch);
        return ResponseEntity.ok().body(businessCards);
    }

    @PostMapping("/api/business-card/register")
    public ResponseEntity<?> registeredCards(@RequestBody RegisteredDto regestedDto,HttpSession httpSession) {
        Long userId =(Long) httpSession.getAttribute("userId");
        return ResponseEntity.ok()
                             .body(businessCardService.registered(userId, regestedDto));
    }

}
