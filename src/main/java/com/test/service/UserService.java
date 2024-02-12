package com.test.service;

import com.test.Exception.ResourceNotFoundException;
import com.test.entity.Post;
import com.test.entity.User;
import com.test.payload.PostDto;
import com.test.payload.UserDto;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User detailById(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow();
        return user;
    }

    public List<User> getAllUser() {

        return  userRepository.findAll();
    }


    public int deleteUser(UserDto userDto) throws Exception{

        User user = userRepository.findById(userDto.getId()).orElseThrow();
       if(user != null){
           userRepository.deleteById(userDto.getId());
           return 1;
       }
       else {
           return 0;
       }


    }

    public User updateUser(UserDto userDto) {

        Long id = userDto.getId();

        User user = userRepository.findById(id);


        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
        return user;
    }

}
}
