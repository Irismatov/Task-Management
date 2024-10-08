package uz.pdp.taskmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.domain.response.LoginResponse;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;
import uz.pdp.taskmanagement.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(String username, String password) {
        UserEntity user = userService.findByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return LoginResponse.builder()
                    .token(jwtService.generateToken(user))
                    .role(user.getRole())
                    .permissions(user.getPermissions())
                    .build();
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    public void registerPA() {
        userRepository.save(UserEntity.builder()
                .role(UserRole.PROJECT_ADMINISTRATOR)
                .username("pa")
                .password(passwordEncoder.encode("1"))
                .permissions(UserRole.PROJECT_ADMINISTRATOR.getPermissions())
                .build());
    }
}
