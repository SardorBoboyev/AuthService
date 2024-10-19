package uz.sb.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.sb.authservice.domain.dto.request.BlockingRequest;
import uz.sb.authservice.servise.block.BlockingService;

@Tag(name = "BlockingController", description = "Operations related to blocking users")
@RestController
@RequestMapping("/api/block")
@RequiredArgsConstructor
public class BlockingController {

    private final BlockingService blockingService;

    @GetMapping("/isBlocked/{blockerId}/{blockedId}")
    @Operation(summary = "Check if a user is blocked")
    public boolean isBlocked(@PathVariable Long blockerId, @PathVariable Long blockedId) {
        return blockingService.isBlocked(blockerId, blockedId);
    }

    @PostMapping("/blocking")
    @Operation(summary = "Block a user")
    public void block(@RequestBody BlockingRequest request) {
        blockingService.add(request);
    }
}
