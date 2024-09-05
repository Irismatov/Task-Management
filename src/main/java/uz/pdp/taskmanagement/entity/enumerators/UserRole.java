package uz.pdp.taskmanagement.entity.enumerators;

import java.util.List;

public enum UserRole {
    CEO (
            List.of(
                    Permission.USER_CREATE,
                    Permission.USER_UPDATE,
                    Permission.USER_READ,
                    Permission.USER_DELETE,
                    Permission.USER_ALL
            )
    );


    //, PRODUCT_OWNER, TEAM_LEAD, HR_ADMIN, PROJECT_ADMINISTRATOR, DEVELOPER, SCRUM_MASTER;

    private final List<Permission> permissions;

    UserRole(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
