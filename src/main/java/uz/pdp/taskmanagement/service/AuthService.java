package uz.pdp.taskmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.domain.JwtResponse;
import uz.pdp.taskmanagement.entity.UserEntity;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public JwtResponse login(String username, String password) {
        UserEntity user = userService.findByUsername(username);
        if(passwordEncoder.matches(password, user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

}
