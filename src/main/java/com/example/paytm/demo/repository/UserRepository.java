package com.example.paytm.demo.repository;

import com.example.paytm.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    @Override
    List<UserModel> findAll();
    public UserModel findByEmailid(String emailID);
    public List<UserModel> findByUsername(String userName);
    public List<UserModel> findByMobilenumber(Long mobileNumber);
}
