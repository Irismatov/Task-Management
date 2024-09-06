package uz.pdp.taskmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.entity.TeamEntity;
import uz.pdp.taskmanagement.entity.domain.request.TeamCreateDTO;
import uz.pdp.taskmanagement.entity.domain.response.TeamResponse;
import uz.pdp.taskmanagement.repository.TeamRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createTeam(TeamCreateDTO teamCreateDTO) {
        TeamEntity team = modelMapper.map(teamCreateDTO, TeamEntity.class);
        teamRepository.save(team);
    }

    public void deleteTeam(UUID teamId) {
        teamRepository.deleteById(teamId);
    }

    public void update(UUID teamId, TeamCreateDTO teamCreateDTO) {
        TeamEntity existingTeam = findById(teamId);

        if (teamCreateDTO.getName() != null) {
            existingTeam.setName(teamCreateDTO.getName());
        }
        if (teamCreateDTO.getDescription() != null) {
            existingTeam.setDescription(teamCreateDTO.getDescription());
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
