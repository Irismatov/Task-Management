package uz.pdp.taskmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private LocalDateTime start;
    private LocalDateTime end;
    @OneToMany
    private List<TaskEntity> tasks;
    @ManyToOne
    private TeamEntity team;
}
