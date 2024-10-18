package uz.sb.authservice.servise.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sb.authservice.domain.dto.request.LoginDto;
import uz.sb.authservice.domain.dto.request.UserRequest;
import uz.sb.authservice.domain.dto.response.JwtResponse;
import uz.sb.authservice.domain.dto.response.UserResponse;
import uz.sb.authservice.domain.entity.UserEntity;
import uz.sb.authservice.exception.DataNotFoundException;
import uz.sb.authservice.repository.AuthRepository;
import uz.sb.authservice.servise.JwtService;


import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse save(UserRequest user) {
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(LocalDate.parse(user.getDateOfBirth()))
                .build();

        try {
            authRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DataNotFoundException("Username already exists");
        }

        UserResponse build = UserResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
        build.setId(userEntity.getId());
        build.setCreatedAt(userEntity.getCreatedDate());
        build.setUpdatedAt(userEntity.getUpdatedDate());
        return build;
    }

    @Override
    public JwtResponse login(LoginDto loginDto) {
        UserEntity userEntity = authRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new DataNotFoundException("Password is incorrect");
        }

        return new JwtResponse(jwtService.generateAccessToken(userEntity),
                jwtService.generateRefreshToken(userEntity));
    }

    @Override
    public UserResponse findById(Long id) {
        UserEntity user = authRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
        UserResponse build = UserResponse.builder()
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .build();
        build.setId(user.getId());
        build.setCreatedAt(user.getCreatedDate());
        build.setUpdatedAt(user.getUpdatedDate());
        return build;
    }
}
