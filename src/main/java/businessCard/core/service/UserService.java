package businessCard.core.service;


import businessCard.core.domain.User;
import businessCard.core.respository.UserRepository;
import businessCard.core.web.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserRequest findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                                           .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));
        return UserRequest.builder()
                          .email(user.getEmail())
                          .name(user.getName())
                          .id(user.getId())
                          .picture(user.getPicture())
                          .build();
    }
}
