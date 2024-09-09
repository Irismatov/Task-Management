package uz.pdp.taskmanagement.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.taskmanagement.filter.JwtFilter;

@Configuration
public class SecurityConfig {
    private final String[] WHITE_LIST = {"/register", "/login", "/"};

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((configurer) -> {
                    configurer
                            .requestMatchers(WHITE_LIST).permitAll()
                            .requestMatchers("/user").hasRole("CEO")

                            .requestMatchers("/features").hasRole("PRODUCT_OWNER")
                            .requestMatchers("/company").hasRole("PRODUCT_OWNER")
                            .requestMatchers("/user/CEO").hasRole("PROJECT_ADMINISTRATOR")
                            .requestMatchers("/product/**").hasRole("CEO")
                            .requestMatchers("/sprint/**").hasRole("CEO")
                            .requestMatchers("/team/**").hasRole("CEO")

                            .anyRequest().authenticated();

                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
