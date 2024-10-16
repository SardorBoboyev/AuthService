package uz.sb.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.sb.authservice.domain.dto.request.LoginDto;
import uz.sb.authservice.domain.dto.request.UserRequest;
import uz.sb.authservice.domain.dto.response.JwtResponse;
import uz.sb.authservice.domain.dto.response.UserResponse;
import uz.sb.authservice.servise.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRequest userRequest) {
        return authService.save(userRequest);
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("/find-by-id/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return authService.findById(id);
    }
}
