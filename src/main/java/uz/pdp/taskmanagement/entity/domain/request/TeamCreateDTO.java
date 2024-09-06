package uz.pdp.taskmanagement.entity.domain.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TeamCreateDTO {
    @Column(unique=true)
    private String name;
    private String description;
    private UUID leadId;
    private UUID scrumMasterId;
}
