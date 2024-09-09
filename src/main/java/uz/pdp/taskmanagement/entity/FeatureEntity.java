package uz.pdp.taskmanagement.entity;


import jakarta.persistence.*;
import lombok.*;
import uz.pdp.taskmanagement.entity.enumerators.FeatureStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "features")
public class FeatureEntity extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne // product owner bo'lishi kerak owner
    private UserEntity owner;

    @Enumerated(EnumType.STRING)
    private FeatureStatus status;
    @OneToMany
    private List<TaskEntity> tasks;
}
