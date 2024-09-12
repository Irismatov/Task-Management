package uz.pdp.taskmanagement.domain.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import uz.pdp.taskmanagement.entity.CompanyEntity;
import uz.pdp.taskmanagement.entity.enumerators.Permission;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Length(min = 3, max = 20)
    private String username;
    @NotBlank
    @Length(min = 3, max = 20)
    private String password;
    private UUID companyId;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private List<Permission> permissions;
}
