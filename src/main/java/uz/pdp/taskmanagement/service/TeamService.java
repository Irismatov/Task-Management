package uz.pdp.taskmanagement.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.domain.request.TeamRequest;
import uz.pdp.taskmanagement.domain.response.TaskResponse;
import uz.pdp.taskmanagement.entity.TeamEntity;
import uz.pdp.taskmanagement.domain.response.TeamResponse;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.repository.TaskRepository;
import uz.pdp.taskmanagement.repository.TeamRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;


    public void createTeam(TeamRequest teamRequest) {
        UserEntity lead = userService.findById(teamRequest.getLead());
        UserEntity scrumMaster = userService.findById(teamRequest.getScrumMaster());
        List<UserEntity> developers = userService.findUserByIds(teamRequest.getDevelopers());

        TeamEntity build = TeamEntity.builder()
                .name(teamRequest.getName())
                .description(teamRequest.getDescription())
                .lead(lead)
                .scrumMaster(scrumMaster)
                .developer(developers)
                .build();

        teamRepository.save(build);
    }

    public void deleteTeam(UUID teamId) {
        UserEntity lead = findById(teamId).getLead();
        lead.setTeam(null);
        userService.updateUser(lead);

        teamRepository.deleteById(teamId);
    }

    public void update(UUID teamId, TeamRequest teamRequest) {
        TeamEntity existingTeam = findById(teamId);

        if (teamRequest.getName() != null) {
            existingTeam.setName(teamRequest.getName());
        }
        if (teamRequest.getDescription() != null) {
            existingTeam.setDescription(teamRequest.getDescription());
        }
        teamRepository.save(existingTeam);
    }

    public List<TeamResponse> getAll() {
        List<TeamEntity> all = teamRepository.findAll();

        return all.stream()
                .map(teamEntity -> new TeamResponse(
                        teamEntity.getId(),
                        teamEntity.getName(),
                        teamEntity.getDescription(),
                        teamEntity.getLead().getUsername(),
                        teamEntity.getScrumMaster().getUsername(),
                        teamEntity.getDeveloper().stream().map(UserEntity::getUsername).toList()
                ))
                .collect(Collectors.toList());
    }


    public TeamEntity findById(UUID teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new BaseException("Team not found"));
    }
}
