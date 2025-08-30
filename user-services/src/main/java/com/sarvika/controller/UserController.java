package com.sarvika.controller;

import com.sarvika.dto.UserRequestDTO;
import com.sarvika.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private  final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity createUser(@RequestBody UserRequestDTO user){
        return  ResponseEntity.ok(userService.saveUser(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateUser( @PathVariable int id,@RequestBody UserRequestDTO users){
         return  ResponseEntity.ok(userService.updateUsers(id,users));
    }
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable int id){
        return  ResponseEntity.ok(userService.fetchUser(id));
    }
    @GetMapping()
    public  ResponseEntity getAllUser(){
        return  ResponseEntity.ok(userService.fetchAllUser());
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity deleteUSers(@PathVariable int id){
        return  ResponseEntity.ok(userService.deleteUsers(id));
    }
}
