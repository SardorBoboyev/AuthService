package uz.sb.authservice.servise.block;

import uz.sb.domain.dto.request.BlockingRequest;

public interface BlockingService {

    void add(BlockingRequest blockingRequest);

    boolean isBlocked(Long blockerId, Long blockedId);

    void unBlock(BlockingRequest blockingRequest);
}
