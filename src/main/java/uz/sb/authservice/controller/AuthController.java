package uz.sb.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.sb.authservice.domain.dto.request.LoginDto;
import uz.sb.authservice.domain.dto.request.UserRequest;
import uz.sb.authservice.domain.dto.response.JwtResponse;
import uz.sb.authservice.domain.dto.response.UserResponse;
import uz.sb.authservice.servise.auth.AuthService;


@Tag(name = "AuthController", description = "Operations related to authentication")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public UserResponse register(@Valid @RequestBody UserRequest userRequest) {
        return authService.save(userRequest);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user")
    public JwtResponse login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping("/find-by-id/{id}")
    @Operation(summary = "Find user by ID")
    public UserResponse findById(@PathVariable Long id) {
        return authService.findById(id);
    }
}
