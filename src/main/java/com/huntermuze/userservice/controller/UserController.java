package com.huntermuze.userservice.controller;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String userID) {
        return ResponseEntity.ok(userService.getUser(userID));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        userService.updateUser(user);
        URI loc = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(loc).build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String userID) {
        userService.deleteUser(userID);
        return ResponseEntity.ok().build();
    }
}
