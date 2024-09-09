package uz.pdp.taskmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.controller.exception.BaseException;
import uz.pdp.taskmanagement.domain.request.SprintRequest;
import uz.pdp.taskmanagement.entity.SprintEntity;
import uz.pdp.taskmanagement.domain.response.SprintResponse;
import uz.pdp.taskmanagement.domain.response.TeamResponse;
import uz.pdp.taskmanagement.entity.TeamEntity;
import uz.pdp.taskmanagement.repository.SprintRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeamService teamService;

    public void createSprint(SprintRequest sprintRequest) {
        SprintEntity entity = SprintEntity.builder()
                .start(sprintRequest.getStart())
                .endTime(sprintRequest.getEndTime())
                .team(teamService.findById(sprintRequest.getTeamId()))
                .build();

        sprintRepository.save(entity);
    }

    public void deleteSprint(UUID sprintId) {
        sprintRepository.deleteById(sprintId);
    }

    public void update(UUID id, SprintRequest sprintRequest) {
        SprintEntity existingSprint = findById(id);

        if (sprintRequest.getStart() != null) {
            existingSprint.setStart(sprintRequest.getStart());
        }
        if (sprintRequest.getEndTime() != null) {
            existingSprint.setEndTime(sprintRequest.getEndTime());
        }
        sprintRepository.save(existingSprint);
    }

    public List<SprintResponse> getAll() {
        return modelMapper.map(sprintRepository.findAll(),
                new TypeReference<List<TeamResponse>>() {
                }.getType());
    }

    public SprintEntity findById(UUID sprintId) {
        return sprintRepository.findById(sprintId)
                .orElseThrow(()-> new BaseException("Sprint not found"));
    }
}
