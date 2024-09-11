package uz.pdp.taskmanagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "teams")
public class TeamEntity extends BaseEntity{
    @Column(unique=true, nullable=false)
    private String name;
    private String description;
    @OneToOne
    private UserEntity lead;
    @OneToOne
    private UserEntity scrumMaster;
    @OneToMany(fetch = FetchType.EAGER)
    private List<UserEntity> developer;
    @OneToMany
    @JsonIgnore
    private List<ProductEntity> products;
}
