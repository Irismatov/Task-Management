package uz.pdp.taskmanagement.entity.enumerators;

import java.util.List;

public enum UserRole {
    PRODUCT_OWNER(List.of(
            Permission.COMPANY_CREATE,
            Permission.COMPANY_DELETE,
            Permission.COMPANY_UPDATE,
            Permission.COMPANY_READ,
            Permission.COMPANY_ALL
    )),

    CEO(List.of(
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_READ,
            Permission.USER_ALL
    )),
    TEAM_LEAD(List.of(
            Permission.TASK_CREATE,
            Permission.TASK_UPDATE,
            Permission.TASK_DELETE,
            Permission.TASK_READ,
            Permission.TASK_ALL,

            Permission.SPRINT_UPDATE,
            Permission.SPRINT_READ
    )),
    HR_ADMIN(List.of(
            Permission.USER_CREATE,
            Permission.USER_UPDATE,
            Permission.USER_DELETE,
            Permission.USER_READ,
            Permission.USER_ALL,

            Permission.TEAM_CREATE,
            Permission.TEAM_UPDATE,
            Permission.TEAM_DELETE,
            Permission.TEAM_READ,
            Permission.TEAM_ALL
    )),
    PROJECT_ADMINISTRATOR(List.of(
            Permission.TASK_CREATE,
            Permission.TASK_UPDATE,
            Permission.TASK_DELETE,
            Permission.TASK_READ,
            Permission.TASK_ALL,

            Permission.FEATURE_CREATE,
            Permission.FEATURE_UPDATE,
            Permission.FEATURE_DELETE,
            Permission.FEATURE_READ,
            Permission.FEATURE_ALL
    )),
    DEVELOPER(List.of(
            Permission.TASK_READ,
            Permission.TASK_UPDATE
    )),
    SCRUM_MASTER(List.of(
            Permission.SPRINT_CREATE,
            Permission.SPRINT_UPDATE,
            Permission.SPRINT_DELETE,
            Permission.SPRINT_READ,
            Permission.SPRINT_ALL
    ));


    private final List<Permission> permissions;

    UserRole(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
