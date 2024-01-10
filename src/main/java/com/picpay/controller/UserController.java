package com.picpay.controller;

import com.picpay.domain.user.User;
import com.picpay.dto.UserDTO;
import com.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService serviceUser;

    @GetMapping
    public ResponseEntity<List<User>> get() {
        System.out.println("All Users");
        return ResponseEntity.ok().body(serviceUser.getUser());
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user) {
        System.out.println("Signing up user");

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUser.postUser(user));
    }

    @GetMapping( "{id}" )
    public ResponseEntity<User> getById(@PathVariable Long id) {
        System.out.println("User with id: " + id);

        return ResponseEntity.ok(serviceUser.getByIdUser(id));
    }

    @DeleteMapping( "{id}" )
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        System.out.println("Deleting user with id " + id);
        serviceUser.deleteByIdUser(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping( "{id}" )
    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody UserDTO user) {
        System.out.println("Changing user with id " + id);

        return ResponseEntity.ok(serviceUser.putUser(id, user));
    }
}
