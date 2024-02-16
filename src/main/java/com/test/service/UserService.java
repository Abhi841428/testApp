package com.test.service;

import com.test.Controller.EmailController;
import com.test.CustumUtil.Util;
import com.test.Exception.DuplicateEmailException;
import com.test.Exception.ResourceNotFoundException;
import com.test.entity.User;
import com.test.payload.EmailSendDto;
import com.test.payload.UserUpdateDto;
import com.test.payload.UserDto;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailController emailController;



    public String signUp(UserDto userDto) {
//        String msgBody =  "Welcome to Java Test App\n\n"
//                + "You are successfully registered.\n\n"
//                + "Your Username: " + userDto.getUserName() + "\n"
//                + "Password: " + userDto.getPassword();
//
//        String body2= "<html><body>" +
//                "<h2>Welcome to Java Test App</h2>" +
//                "<p>You are successfully registered.</p>" +
//                "<p><strong>Your Username:</strong> " + userDto.getUserName()+ "</p>" +
//                "<p><strong>Password:</strong> " + userDto.getPassword() + "</p>" +
//                "<p>Regards,<br/>" +
//                "JAVA TEST APP" +
//                "</p>" +
//                "<img src='cid:logo' width='200px'/>" +
//                "</body></html>";

        String body3 ="<html><head><style>" +
                "body { background-color: #f2f2f2; }" + // Change the background color to your desired color
                "h2 {color:#ed3109; } "     +
                "p {color:#1349c1; } "     +
                "</style></head><body>" +
                "<h2>Welcome to Java Test App</h2>" +
                "<p><strong>YOU CAN USE THIS CREDENTIALS FOR LOGIN:-</strong> </p>" +
                "<p><strong>Your Username:</strong> " + userDto.getUserName() + "</p>" +
                "<p><strong>Password:</strong> " + userDto.getPassword() + "</p>" +
                "<p>Regards,<br/>" +
                "JAVA TESTING APP.\n " +
                "SRISTI SINGH"+
                "</p>" +
                "<img src='cid:logo' width='200px'/>" +
                "</body></html>";
        String subject =" JAVA TEST APP REGISTRATION";
        try {

            if(!Util.isValidEmail(userDto.getEmail())){
                return "please Enter Valid Email Id";
            }
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            userRepository.save(user);


            EmailSendDto email = new EmailSendDto();
            email.setRecipient(user.getEmail());
            email.setMsgBody(body3);
            email.setSubject(subject);
            emailController.sendModifiedEmail(email);
        }
        catch (DataIntegrityViolationException e){
           return " email already Registered";
        }
        return "User registered successfully";
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

    public String updateUser(UserUpdateDto userUpdateDto) {

        String message = "";

        User user = userRepository.findByuserName(userUpdateDto.getUserName());

        if(user == null){
            message = "User Not Found";
            return message;

        }
        if(userUpdateDto.getPassword() == null){
            message = "Invalid Password";
            return message;

        }

        if(!userUpdateDto.getPassword().equals(user.getPassword())){
            message="Invalid Username and password";

            return message;
        }

            user.setUserName(userUpdateDto.getNewUserName());
            user.setPassword(userUpdateDto.getNewPassword());



        userRepository.save(user);
        return message;
    }

}

