package businessCard.core.controller;

import businessCard.core.config.oauth.LoginUser;
import businessCard.core.config.oauth.dto.SessionUser;
import businessCard.core.service.BusinessCardService;
import businessCard.core.service.UserService;
import businessCard.core.web.dto.UserRequest;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BusinessCardService businessCardService;
    private final UserService userService;


    @GetMapping("/")
    //main page
    public String main(Model model, @LoginUser SessionUser user) {

        model.addAttribute("posts", businessCardService.findAllDesc());

        /**
         * CustomOAuth2Service 에서 로그인 성공시 세션을 SessionUser 클래스에 저장
         * .getAttribute("user") 를 통하여 값 가져오기 가능
         */
        //SessionUser user = (SessionUser)httpSession.getAttribute("user");
        /**
         * 로그인 성공시에만 userName 등록
         */
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/business-card/save")
    public String saveForm() {

        return "business-card";
    }
}
