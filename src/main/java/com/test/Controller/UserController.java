package com.test.Controller;

import com.test.Exception.DuplicateEmailException;
import com.test.Exception.ResourceNotFoundException;
import com.test.entity.User;
import com.test.payload.UserUpdateDto;
import com.test.payload.UserDto;
import com.test.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/signUp")
    public ResponseEntity<String> createPost( @RequestBody UserDto userDto){


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

    @GetMapping(value = "/detail")
    public ResponseEntity<User> detailById(@RequestBody UserDto userDto){
        User user = userService.detailById(userDto);
        return  new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping(value = "/all")
    public List<User> getAllUser(){
        List<User> userList = userService.getAllUser();
        return  userList;
    }

  @DeleteMapping(value = "/delete")
    public String deleteUser(@RequestBody UserDto userDto){
        String message =" ";
        try {
            int deletedRow = userService.deleteUser(userDto);
            if(deletedRow>0){
                message ="User Deeleted successfully";
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e){
            message="User Not found with id" + "-"+ userDto.getId();
        }
      return  message;
  }

  @PutMapping(value = "/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
      String message = userService.updateUser(userUpdateDto);
      return  new ResponseEntity<>(message,HttpStatus.FOUND);


  }



}
