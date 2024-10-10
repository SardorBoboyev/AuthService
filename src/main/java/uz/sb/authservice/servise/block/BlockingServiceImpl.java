package uz.sb.authservice.servise.block;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sb.authservice.domain.entity.BlockingEntity;
import uz.sb.authservice.exception.DataNotFoundException;
import uz.sb.authservice.repository.AuthRepository;
import uz.sb.authservice.repository.BlockingRepository;
import uz.sb.domain.dto.request.BlockingRequest;


@Service
@RequiredArgsConstructor
public class BlockingServiceImpl implements BlockingService {

    private final BlockingRepository blockingRepository;
    private final AuthRepository authRepository;


    @Override
    public void add(BlockingRequest blockingRequest) {
        if (authRepository.findById(blockingRequest.getBlockerUserId()).isEmpty()) {
            throw new DataNotFoundException("Blocker user not found");
        }

        if (authRepository.findById(blockingRequest.getBlockedUserId()).isEmpty()) {
            throw new DataNotFoundException("Blocked user not found");
        }

        if (blockingRepository.existsByBlockerUserIdAndBlockedUserId(
                blockingRequest.getBlockerUserId(), blockingRequest.getBlockedUserId())) {
            throw new DataNotFoundException("User is already blocked");
        }
        BlockingEntity blockingEntity = BlockingEntity.builder()
                .blockerUserId(blockingRequest.getBlockerUserId())
                .blockedUserId(blockingRequest.getBlockedUserId())
                .build();
        blockingRepository.save(blockingEntity);
    }


    @Override
    public boolean isBlocked(Long blockerId, Long blockedId) {
        return blockingRepository.existsByBlockerUserIdAndBlockedUserId(blockerId, blockedId);
    }

    @Override
    public void unBlock(BlockingRequest blockingRequest) {

    }
}
