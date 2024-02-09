package com.test.Controller;

import com.test.Exception.ResourceNotFoundException;
import com.test.entity.User;
import com.test.payload.PostDto;
import com.test.payload.UserDto;
import com.test.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signUp")
    public ResponseEntity<User> createPost(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        try {
            String message = userService.login(userDto);
            return  new ResponseEntity<>(message,HttpStatus.FOUND);
        }
       catch (ResourceNotFoundException e){

           return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
}
