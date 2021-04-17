package com.example.paytm.demo.controller;

import com.example.paytm.demo.helpers.UtilityMethods;
import com.example.paytm.demo.model.UserModel;
import com.example.paytm.demo.model.WalletModel;
import com.example.paytm.demo.service.UserService;
import com.example.paytm.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    //will create wallet for a user
    @PostMapping(value = "/wallet")
    public String addWallet(@RequestBody WalletModel walletModel) {
        List<WalletModel> phone_number = walletService.findByPhone(walletModel.getPhone()); // check for same phone number
        List<UserModel> User_number = userService.findbyMobileNumber(walletModel.getPhone());
        if(!User_number.isEmpty()) {
            if (phone_number.isEmpty()) {
                walletService.addWallet(walletModel);
                logger.log(Level.INFO, "Wallet created at "+ UtilityMethods.get_current_time());
                return "Wallet Created";

            } else return "Wallet already exists";
        }
        else return "Phone not registered";
    }



    //for displaying all the wallets present in the database
    @GetMapping(value = "/wallet/all")
    public List<WalletModel> displayAll() {
//        System.out.println("sever 1");
        logger.log(Level.INFO, "List of wallets returned at "+ UtilityMethods.get_current_time());
        return walletService.getWallets();
    }
}
