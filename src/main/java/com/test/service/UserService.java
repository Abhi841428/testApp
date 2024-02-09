package com.test.service;

import com.test.Exception.ResourceNotFoundException;
import com.test.entity.User;
import com.test.payload.PostDto;
import com.test.payload.UserDto;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User signUp(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public String login(UserDto userDto) throws ResourceNotFoundException{
        String message =" ";
        User user = userRepository.findByuserName(userDto.getUserName());
        if(user==null){
           throw  new ResourceNotFoundException("User Not Found","with User Name"+" -"+userDto.getUserName(), HttpStatus.BAD_REQUEST.value());
//            message="User Not Found";
//            return message;
        }
        if(userDto.getPassword().equals(user.getPassword())){
            message="Login Succesfull";
        }
        else {
            throw  new ResourceNotFoundException("Your password is incorrect","Please Try Again", HttpStatus.BAD_REQUEST.value());

        }
        return message;
    }
}
