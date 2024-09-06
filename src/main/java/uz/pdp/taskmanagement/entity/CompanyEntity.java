package uz.pdp.taskmanagement.entity;


import jakarta.persistence.Entity;
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
}
