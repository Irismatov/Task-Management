package uz.pdp.taskmanagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "teams")
public class TeamEntity extends BaseEntity{
    private String name;
    private String description;
    @OneToOne
    private UserEntity lead;
    @OneToOne
    private UserEntity scrumMaster;
    @OneToMany
    @JsonIgnore
    private List<ProductEntity> products;
}
