package uz.pdp.taskmanagement.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.taskmanagement.entity.UserEntity;

@RestController()
@RequestMapping("/test")
public class TestController {

    @PostMapping()
    public UserEntity save() {
        return null;
    }

    @DeleteMapping("{update}")
    public void delete() {
    }

    @PutMapping()
    public UserEntity update() {
        return null;
    }

    @GetMapping("/{id}")
    public UserEntity get() {
        return null;
    }


    ///all
    @GetMapping()
    public UserEntity getAll() {
        return null;
    }



}
