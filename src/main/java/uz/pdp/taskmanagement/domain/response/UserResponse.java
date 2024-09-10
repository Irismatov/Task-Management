package uz.pdp.taskmanagement.domain.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.taskmanagement.entity.enumerators.Permission;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private List<Permission> permissions;
}
