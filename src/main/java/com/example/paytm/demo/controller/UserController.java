package com.example.paytm.demo.controller;

import com.example.paytm.demo.helpers.UtilityMethods;
import com.example.paytm.demo.model.UserModel;
import com.example.paytm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping("/loginUser")
    public String loginUser() {
        logger.log(Level.INFO, "User logged in at "+ UtilityMethods.get_current_time());
        return "User logged in";
    }

    @PostMapping(value = "/user")
    public String addUser(@RequestBody UserModel user) {
        logger.log(Level.INFO, "User Registered at "+ UtilityMethods.get_current_time());
        UserModel userEmail = userService.findByEmailID(user.getEmailid());
        List<UserModel> userUsername = userService.findbyUserName(user.getUsername());
        List<UserModel> userMobileNumber = userService.findbyMobileNumber(user.getMobilenumber());

        // checking for existing Users
        if(userEmail!=null)   {return "User with same emailID already exists";}
        else if (!userUsername.isEmpty() ) {return "User with same userName already exists";}
        else if (!userMobileNumber.isEmpty()) {return "User with same mobileNumber already exists";}
        else  {userService.addUser(user);}
        return "User saved";

    }

    @GetMapping("users")
    public List<UserModel> getUsers() {
        logger.log(Level.INFO, "list of all users returned at "+ UtilityMethods.get_current_time());
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{userId}")
    public UserModel getUser(@PathVariable("userId") int userId) {
        return userService.getUser(userId);
    }

    @PutMapping(value = "/user/{userId}")
    public String updateUser(@PathVariable("userId") int userId, @RequestBody UserModel user) {
        logger.log(Level.INFO, "User updated at "+ UtilityMethods.get_current_time());
        UserModel existingUser = userService.updateUser(userId,user);
        if(existingUser == null)
            return "user not existed";
        else return "user data updated";
    }

    @DeleteMapping(value = "/user/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        logger.log(Level.INFO, "User deleted at "+ UtilityMethods.get_current_time());
        if (userService.getUser(userId) != null) {
            userService.deleteUser(userId);
            return "User data deleted";
        } else {
            return "User doesn't exist";

        }

    }
}
