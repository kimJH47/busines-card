package businessCard.core.config.oauth.dto;

import businessCard.core.domain.Role;
import businessCard.core.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object>  attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;


    //빌더패턴 사용
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


    /**
     * OAuthAttributes 의 반환하는 사용자 정보는 Map(attributes) 형태로 전부 포함되기 때문에 값(필드) 하나하나를 변환해야함
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                              .name((String) response.get("name"))
                              .email((String) response.get("email"))
                              .picture((String) response.get("profile_image"))
                              .attributes(response)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                              .name((String) attributes.get("name"))
                              .email((String) attributes.get("email"))
                              .picture((String) attributes.get("picture"))
                              .attributes(attributes)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object>attributes) {

        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuthAttributes.builder()
                              .name((String) profile.get("nickname"))
                              .email((String) account.get("email"))
                              .picture((String) profile.get("profile_image_url"))
                              .attributes(attributes)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }

    /**
     * User 앤티티 생성
     * 앤티티를 생성하는 시점은 처음  가입할때
     * 가입할때의 기본권한은 Role.GUEST 부여
     *
     */
    public User toEntity() {
        return User.builder()
                   .name(name)
                   .email(email)
                   .picture(picture)
                   .role(Role.USER)
                   .build();
    }
}
