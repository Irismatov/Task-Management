package uz.pdp.taskmanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.domain.request.TaskRequest;
import uz.pdp.taskmanagement.domain.response.TaskResponse;
import uz.pdp.taskmanagement.entity.TaskEntity;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TaskResponse createTask(TaskRequest taskRequest) {
        TaskEntity task = modelMapper.map(taskRequest, TaskEntity.class);
        TaskEntity save = taskRepository.save(task);
        return modelMapper.map(save, TaskResponse.class);
    }

    public List<TaskResponse> getAll() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream().map(all -> modelMapper.map(all, TaskResponse.class)).toList();
    }

    public TaskResponse getTaskById(UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return modelMapper.map(task, TaskResponse.class);
    }


    public TaskResponse updateTask(TaskRequest taskRequest, UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        modelMapper.map(taskRequest, task);
        TaskEntity save = taskRepository.save(task);
        return modelMapper.map(save, TaskResponse.class);
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<TaskResponse> getAllTasksForTeamLead(UserEntity teamLead) {
        return getAllTasksByTeamId(teamLead.getTeam().getId());
    }

    List<TaskResponse> getAllTasksByTeamId(UUID teamId) {
        return modelMapper.map(taskRepository.getAllTasksByTeamId(teamId), new TypeReference<List<TaskResponse>>(){}.getType());
    }
}
