package uz.pdp.taskmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.domain.request.TeamRequest;
import uz.pdp.taskmanagement.entity.TeamEntity;
import uz.pdp.taskmanagement.domain.response.TeamResponse;
import uz.pdp.taskmanagement.repository.TeamRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createTeam(TeamRequest teamRequest) {
        TeamEntity team = modelMapper.map(teamRequest, TeamEntity.class);
        teamRepository.save(team);
    }

    public void deleteTeam(UUID teamId) {
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
        return modelMapper.map(teamRepository.findAll(),
                new TypeReference<List<TeamResponse>>() {
        }.getType());
    }

    public TeamEntity findById(UUID teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new BaseException("Team not found"));
    }
}
