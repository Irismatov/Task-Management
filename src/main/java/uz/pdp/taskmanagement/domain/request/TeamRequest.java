package uz.pdp.taskmanagement.domain.request;

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

public class TeamRequest {
    @Column(unique=true)
    private String name;
    private String description;
    private UUID lead;
    private UUID scrumMaster;
}
