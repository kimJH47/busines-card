package businessCard.core.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
public class BusinessCardUpdateDto {
    @NotNull
    private Long id;
    private String name;
    private String tell;
    private String Role;
    private String company;
    private String email;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime uploadTime;

}
