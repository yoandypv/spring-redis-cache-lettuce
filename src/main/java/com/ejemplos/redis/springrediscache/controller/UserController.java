package com.ejemplos.redis.springrediscache.controller;

import com.ejemplos.redis.springrediscache.entities.User;
import com.ejemplos.redis.springrediscache.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public User save(@RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping
    public List<User> findAll(){
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return this.userService.findById(id);
    }

    @PatchMapping("/{id}/{salary}")
    public User updateSalary(@PathVariable Long id, @PathVariable float salary){
        return this.userService.updateSalary(id,salary);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
         this.userService.deleteById(id);
    }

}
