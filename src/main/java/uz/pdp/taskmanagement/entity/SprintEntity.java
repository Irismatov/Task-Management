package uz.pdp.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "sprints")
public class SprintEntity extends BaseEntity {

    @Column(unique = true)
    private LocalDateTime start;
    private LocalDateTime endTime;
    @OneToMany
    private List<TaskEntity> tasks;
    @ManyToOne
    private TeamEntity team;
}
