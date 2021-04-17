package com.example.paytm.demo.repository;

import com.example.paytm.demo.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<WalletModel,Long> {
    List<WalletModel> findAll();
    public List<WalletModel> findByPhone(long phone);
}
