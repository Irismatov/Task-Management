package uz.pdp.taskmanagement.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.taskmanagement.domain.response.TaskResponse;
import uz.pdp.taskmanagement.entity.enumerators.FeatureStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeatureRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String owner;
    @NotBlank
    private FeatureStatus status;
    @NotBlank
    private List<TaskResponse> tasks;

}
