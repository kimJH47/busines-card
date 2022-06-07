package businessCard.core.controller;

import businessCard.core.service.BusinessCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BusinessCardService businessCardService;

}
