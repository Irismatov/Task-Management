package uz.pdp.taskmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uz.pdp.taskmanagement.entity.enumerators.Permission;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isBlocked;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;
    @ManyToOne
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }
}
