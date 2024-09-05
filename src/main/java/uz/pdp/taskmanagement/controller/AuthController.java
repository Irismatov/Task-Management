package uz.pdp.taskmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.taskmanagement.domain.JwtResponse;
import uz.pdp.taskmanagement.domain.request.LoginRequest;
import uz.pdp.taskmanagement.domain.request.UserRequest;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.service.AuthService;
import uz.pdp.taskmanagement.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest login) {
        return authService.login(login.getUsername(), login.getPassword());
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRequest request) {
        return userService.save(request);
    }

}
