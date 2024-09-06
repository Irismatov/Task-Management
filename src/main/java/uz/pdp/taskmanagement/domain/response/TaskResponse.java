package uz.pdp.taskmanagement.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.taskmanagement.entity.FeatureEntity;
import uz.pdp.taskmanagement.entity.SprintEntity;
import uz.pdp.taskmanagement.entity.TeamEntity;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.entity.enumerators.TaskStatus;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private UUID taskId;
    private String name;
    private String description;
    private int points;
    private String feature;
    private String owner;
    private String team;
    private TaskStatus status;
//    private SprintEntity sprint;
}
