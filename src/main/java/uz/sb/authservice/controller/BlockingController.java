package uz.sb.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.sb.authservice.servise.block.BlockingService;
import uz.sb.domain.dto.request.BlockingRequest;

@RestController
@RequestMapping("/api/block")
@RequiredArgsConstructor
public class BlockingController {

    private final BlockingService blockingService;

    @GetMapping("/isBlocked/{blockerId}/{blockedId}")
    public boolean isBlocked(@PathVariable Long blockerId, @PathVariable Long blockedId) {
        return blockingService.isBlocked(blockerId, blockedId);
    }

    @PostMapping("/blocking")
    public void block(@RequestBody BlockingRequest request) {
        blockingService.add(request);
    }
}
