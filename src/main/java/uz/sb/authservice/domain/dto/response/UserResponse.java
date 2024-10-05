package uz.sb.authservice.domain.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse extends BaseResponse {
private String username;
private String firstName;
private String lastName;
private String phoneNumber;
}
