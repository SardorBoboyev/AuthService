package uz.sb.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sb.authservice.domain.entity.BlockingEntity;

@Repository
public interface BlockingRepository extends JpaRepository<BlockingEntity, Long> {

    boolean existsByBlockerUserIdAndBlockedUserId(Long blockerUserId, Long blockedUserId);
}
