package uz.pdp.taskmanagement.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.taskmanagement.controller.exception.ExpiredTokenException;
import uz.pdp.taskmanagement.service.JwtService;
import uz.pdp.taskmanagement.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }



        String token = authorization.substring(7);

        //validate token
        Jws<Claims> claimsJws;

        try {
            claimsJws = jwtService.validateToken(token);
        } catch (ExpiredTokenException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\" : \" " + e.getMessage() + "\"}");
            return;
        }

        Claims payload = claimsJws.getPayload();
        List<SimpleGrantedAuthority> authorities = payload.get("authorities", ArrayList.class)
                .stream()
                .map((authority) -> new SimpleGrantedAuthority((String) authority))
                .toList();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userService.findByUsername(payload.getSubject()),
                        null,
                        authorities
                );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
