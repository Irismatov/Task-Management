package uz.pdp.taskmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.UserRequest;
import uz.pdp.taskmanagement.domain.response.UserResponse;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;
import uz.pdp.taskmanagement.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    private UserEntity save(@RequestBody UserRequest user){
        return userService.save(user);
    }

    @DeleteMapping("/{userId}")
    private void delete(@PathVariable("userId")UUID id) {
        userService.delete(id);
    }

    @PutMapping("{userId}")
    private UserEntity update(@PathVariable("userId")UUID id, @RequestBody UserRequest user) {
        return userService.update(id, user);
    }

    @GetMapping("{userId}")
    private UserEntity get(@PathVariable("userId")UUID id) {
        return userService.findById(id);
    }

    @GetMapping("/ceo")
    private List<UserResponse> getCEOs() {
        return userService.findByRole(UserRole.CEO);
    }

    @GetMapping("/leads")
    private List<UserResponse> getLeads() {
        return userService.getAllTeamLeads();
    }


    @GetMapping("/scrum-master")
    private List<UserResponse> getScrumMasters() {
        return userService.getAllTeamScrumMasters();
    }

    @GetMapping("/getAllProductOwnersAndProductIsNull")
    private List<UserResponse> getAllByRoleAndTeamIsNull() {
        return userService.getAllProductOwnersAndProductIsNull();
    }

    @PostMapping("/save-hr-admin")
    private UserEntity saveHRAdmin(@RequestBody UserRequest user) {
        return userService.save(user);
    }

    @GetMapping("/get-hr-admin")
    private List<UserResponse> getHRAdmin() {
        return userService.findByRole(UserRole.HR_ADMIN);
    }

    @DeleteMapping("/delete-hr-admin/{id}")
    private void deleteHRAdmin(@PathVariable UUID id) {
        userService.delete(id);
    }

    @PostMapping("/add-employee")
    private UserEntity addEmployee(@RequestBody UserRequest user) {
        return userService.saveEmployee(user);
    }


    @GetMapping("/get-employee")
    private List<UserResponse> getEmployees() {
        return userService.findEmployees(List.of(UserRole.TEAM_LEAD, UserRole.SCRUM_MASTER, UserRole.DEVELOPER));
    }

    @DeleteMapping("/delete-employee/{id}")
    private void deleteEmployee(@PathVariable("id") UUID id) {
        userService.delete(id);
    }



    @GetMapping("/get-product-owner")
    private List<UserResponse> getProductOwner() {
        return userService.findByRole(UserRole.PRODUCT_OWNER);
    }

}
