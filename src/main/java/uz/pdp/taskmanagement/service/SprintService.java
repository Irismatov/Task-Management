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
import uz.pdp.taskmanagement.repository.SprintRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createSprint(SprintRequest sprintRequest) {
        if (sprintRepository.existsByStart(sprintRequest.getStart())) {
            throw new BaseException("This date already exists");
        }
            SprintEntity sprint = modelMapper.map(sprintRequest, SprintEntity.class);
            sprintRepository.save(sprint);


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
