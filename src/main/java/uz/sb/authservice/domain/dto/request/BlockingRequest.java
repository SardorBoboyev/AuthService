package uz.sb.authservice.domain.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BlockingRequest {
    private Long blockerUserId;
    private Long blockedUserId;
}
