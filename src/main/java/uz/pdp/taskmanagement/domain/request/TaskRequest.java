package uz.pdp.taskmanagement.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.taskmanagement.entity.enumerators.TaskStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {
    private String name;
    private String description;
    private int points;
    private String feature;
    private String owner;
    private String team;
    private TaskStatus status;
//    private SprintEntity sprint;
}
