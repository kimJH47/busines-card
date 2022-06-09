package businessCard.core.controller;


import businessCard.core.service.BusinessCardService;
import businessCard.core.web.dto.BusinessCardSaveDto;
import businessCard.core.web.dto.BusinessCardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class businessCardController {

    private final BusinessCardService businessCardService;



    //명함저장 Api, 유저ID는 Dto에 포함
    @PostMapping("/api/business-card")
    public Long save(BusinessCardSaveDto businessCardSaveDto) {
       return businessCardService.save(businessCardSaveDto);
    }

    @PutMapping("/api/business-card/{id}")
    public void update(@PathVariable Long id, @RequestBody BusinessCardUpdateDto requestDto) {
        businessCardService.update(id, requestDto);
    }

    @DeleteMapping("/api/business-card/{id}")
    public Long delete(@PathVariable Long id) {
        businessCardService.delete(id);
        return id;
    }
}