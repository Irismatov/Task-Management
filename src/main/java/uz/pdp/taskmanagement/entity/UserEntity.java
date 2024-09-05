package uz.pdp.taskmanagement.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.taskmanagement.entity.enumerators.Permission;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isBlocked;
    @ManyToOne
    private TeamEntity team;
    @ManyToOne
    private CompanyEntity company;
    private UserRole role;
    @ElementCollection
    private List<Permission> permissions;

}
