package uz.pdp.taskmanagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.pdp.taskmanagement.entity.enumerators.FeatureStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "products")
public class ProductEntity extends BaseEntity{
    private String name;
    private String gitRepo;
    private String description;
    @OneToOne
    private UserEntity owner;
    @ManyToOne
    private TeamEntity team;
}
