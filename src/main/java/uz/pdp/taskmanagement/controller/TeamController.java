package uz.pdp.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.TeamRequest;
import uz.pdp.taskmanagement.domain.response.TeamResponse;
import uz.pdp.taskmanagement.service.TeamService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/save-team")
    private ResponseEntity<Void> saveTeam(@RequestBody TeamRequest teamRequest) {
        teamService.createTeam(teamRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-team")
    private List<TeamResponse> getAllTeams() {
        return teamService.getAll();
    }

    @DeleteMapping("delete-team/{teamId}")
    private ResponseEntity<Void> deleteTeamById(@PathVariable("teamId") UUID teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-team/{teamId}")
    private ResponseEntity<Void> updateTeam(@PathVariable("teamId") UUID id,
                                            @RequestBody TeamRequest teamRequest) {
        teamService.update(id, teamRequest);
        return ResponseEntity.ok().build();
    }

}
