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
    private final String[] WHITE_LIST = {"/users", "/users/**", "/register", "/login", "/", "/register-pa"};

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((configurer) -> {
                    configurer
                            .requestMatchers(WHITE_LIST).permitAll()
                            .requestMatchers("/users").hasRole("CEO")

                            .requestMatchers("/features").hasRole("PRODUCT_OWNER")
//                            .requestMatchers("/company/**").hasRole("PROJECT_ADMINISTRATOR")
                            .requestMatchers("/user/CEO").hasRole("PROJECT_ADMINISTRATOR")
                            .requestMatchers("/product",
                                    "/product/**",
                                    "/users/getAllProductOwnersAndProductIsNull",
                                    "/team/get-team",
                                    "/sprint/**",
                                    "/users/get-product-owner"
                            ).hasRole("CEO")
                            .requestMatchers("/product/**").hasRole("CEO")
                            .requestMatchers("/sprint/**").hasRole("CEO")
                            .requestMatchers("/tasks/team-lead").hasRole("TEAM_LEAD")
                            .requestMatchers("/team/save-team", "/team/", "team/update-team/", "team/delete-team/", "/team/get-team").hasRole("HR_ADMIN")
                            .requestMatchers("users/save-hr-admin", "users/get-hr-admin", "users/delete-hr-admin/").hasRole("CEO")

                            .anyRequest().authenticated();

                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
