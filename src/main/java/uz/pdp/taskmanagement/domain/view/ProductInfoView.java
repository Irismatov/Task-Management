package uz.pdp.taskmanagement.domain.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfoView {
    private UUID id;
    private String name;
    private String gitRepo;
    private String description;
    private UUID ownerId;
    private UUID  teamId;
}

