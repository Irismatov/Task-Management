package uz.pdp.taskmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.taskmanagement.domain.response.LoginResponse;
import uz.pdp.taskmanagement.domain.request.LoginRequest;
import uz.pdp.taskmanagement.service.AuthService;
import uz.pdp.taskmanagement.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest login) {
        return authService.login(login.getUsername(), login.getPassword());
    }

    @GetMapping("/register-pa")
    public void register() {
        authService.registerPA();
    }

}
