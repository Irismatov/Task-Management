package uz.pdp.taskmanagement.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SprintResponse {
    private LocalDateTime start;
    private LocalDateTime end;

}
