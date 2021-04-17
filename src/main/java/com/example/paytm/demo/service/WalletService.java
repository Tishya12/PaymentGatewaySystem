package com.example.paytm.demo.service;

import com.example.paytm.demo.model.WalletModel;
import com.example.paytm.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    //method for adding wallet in the database
    public WalletModel addWallet(WalletModel walletModel) {
        return walletRepository.save(walletModel);
    }

    //method for getting all the wallets from the database
    public List<WalletModel> getWallets() {
        return walletRepository.findAll();
    }

    //it will return object of walletmodel type if phone is present
    public List<WalletModel> findByPhone(long phone) {return walletRepository.findByPhone(phone);}
}
