package uz.pdp.taskmanagement.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyCreateDTO {
    private UUID ceoId;
    private String name;
    private String address;
    private LocalDate establishmentDate;
}
