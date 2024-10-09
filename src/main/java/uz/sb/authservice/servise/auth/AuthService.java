package uz.sb.authservice.servise.auth;

import uz.sb.domain.dto.request.LoginDto;
import uz.sb.domain.dto.request.UserRequest;
import uz.sb.domain.dto.response.JwtResponse;
import uz.sb.domain.dto.response.UserResponse;

public interface AuthService {

    UserResponse save(UserRequest user);

    JwtResponse login(LoginDto loginDto);

    UserResponse findById(Long id);
}
