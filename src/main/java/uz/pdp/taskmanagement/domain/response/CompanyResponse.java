package uz.pdp.taskmanagement.domain.response;

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
public class CompanyResponse {
    private UUID id;
    private UUID ceoId;
    private String name;
    private String address;
    private LocalDate establishmentDate;
    private Boolean isBlocked;
}
