package uz.pdp.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.entity.domain.request.TeamCreateDTO;
import uz.pdp.taskmanagement.entity.domain.response.TeamResponse;
import uz.pdp.taskmanagement.service.TeamService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    private ResponseEntity<Void> saveTeam(@RequestBody TeamCreateDTO teamCreateDTO) {
        teamService.createTeam(teamCreateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private List<TeamResponse> getAllTeams() {
        return teamService.getAll();
    }

    //htttp ... ./team/i3jfklejfklsdjfsjflkfwlkfj324
    @DeleteMapping("/{teamId}")
    private ResponseEntity<Void> deleteTeamById(@PathVariable("teamId") UUID teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{teamId}")
    private ResponseEntity<Void> updateTeam(@PathVariable("teamId") UUID id,
                                            @RequestBody TeamCreateDTO teamCreateDTO) {
        teamService.update(id, teamCreateDTO);
        return ResponseEntity.ok().build();
    }

}
