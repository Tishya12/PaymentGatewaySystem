package com.example.paytm.demo.service;

import com.example.paytm.demo.model.UserModel;
import com.example.paytm.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public UserModel getUser(int userId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public UserModel updateUser(int userId, UserModel user) {
        UserModel existingUser=userRepository.findById(user.getId()).orElse(null);
        System.out.println("id is "+existingUser.getId());
        existingUser.setUsername(user.getUsername());
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setMobilenumber(user.getMobilenumber());
        existingUser.setEmailid(user.getEmailid());
        existingUser.setAddress(user.getAddress());
        return userRepository.save(existingUser);

    }

    public String deleteUser(int userId) {
        userRepository.deleteById(userId);
        return "user removed!!";
    }
    public UserModel findByEmailID(String emailid) { return userRepository.findByEmailid(emailid); }
    public List<UserModel> findbyUserName(String username) { return userRepository.findByUsername(username); }
    public List<UserModel> findbyMobileNumber(Long mobilenumber) {return userRepository.findByMobilenumber(mobilenumber);}
}
