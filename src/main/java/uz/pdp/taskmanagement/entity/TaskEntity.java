package uz.pdp.taskmanagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.taskmanagement.entity.enumerators.TaskStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tasks")
public class TaskEntity extends BaseEntity{
    private String name;
    private String description;
    private int points;
    @ManyToOne
    private FeatureEntity feature;
    @ManyToOne //dev or team lead
    private UserEntity owner;
    @ManyToOne
    private TeamEntity team;
    private TaskStatus status;
    @ManyToOne
    private SprintEntity sprint;


}
