package uz.pdp.taskmanagement.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.TaskRequest;
import uz.pdp.taskmanagement.domain.response.TaskResponse;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.service.JwtService;
import uz.pdp.taskmanagement.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{taskId}")
    public TaskResponse getTask(@PathVariable UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@RequestBody TaskRequest taskRequest,
                                   @PathVariable UUID taskId) {
        return taskService.updateTask(taskRequest, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/team-lead")
    public List<TaskResponse> getTasks(@RequestHeader("authorization") String token) {
        UserEntity userFromToken = jwtService.getUserFromToken(token);
        return taskService.getAllTasksForTeamLead(userFromToken);
    }

}
