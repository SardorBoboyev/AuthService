package uz.sb.authservice.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthControllerUI {
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "/sign-up";
    }

    @GetMapping("/auth")
    public String auth() {
        return "/auth";
    }
}
