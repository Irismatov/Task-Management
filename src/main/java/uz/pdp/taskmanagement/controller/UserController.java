package uz.pdp.taskmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.domain.request.UserRequest;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{role}")
    private UserEntity save(@RequestBody UserRequest user){
        return userService.save(user);
    }

    @DeleteMapping("{userId}")
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
}
