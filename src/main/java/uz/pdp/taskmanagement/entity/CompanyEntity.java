package uz.pdp.taskmanagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "companies")
public class CompanyEntity extends BaseEntity{
    private String name;
    private String address;
    private LocalDate establishmentDate;
    private Boolean isBlocked;
    @OneToOne
    private UserEntity CEO;
}
