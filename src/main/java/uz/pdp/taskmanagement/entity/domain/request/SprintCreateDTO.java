package uz.pdp.taskmanagement.entity.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SprintCreateDTO {
    private LocalDateTime start;
    private LocalDateTime end;

    private UUID teamId;
}
