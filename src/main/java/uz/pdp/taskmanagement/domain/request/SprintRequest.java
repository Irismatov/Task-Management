package uz.pdp.taskmanagement.domain.request;

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
public class SprintRequest {
    private LocalDateTime start;
    private LocalDateTime endTime;

    private UUID teamId;
}
