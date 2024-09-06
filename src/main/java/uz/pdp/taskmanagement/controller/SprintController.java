package uz.pdp.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.entity.domain.request.SprintCreateDTO;
import uz.pdp.taskmanagement.entity.domain.response.SprintResponse;
import uz.pdp.taskmanagement.service.SprintService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @PostMapping
    private ResponseEntity<Void> saveSprint(@RequestBody SprintCreateDTO sprintCreateDTO) {
        sprintService.createSprint(sprintCreateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private List<SprintResponse> getAllSprints() {
        return sprintService.getAll();
    }

    @PutMapping("/id")
    private ResponseEntity<Void> updateSprint(@RequestParam("id") UUID id, @RequestBody SprintCreateDTO sprintCreateDTO) {
        sprintService.update(id, sprintCreateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id")
    private ResponseEntity<Void> deleteSprint(@RequestParam("id") UUID id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.ok().build();
    }
}
