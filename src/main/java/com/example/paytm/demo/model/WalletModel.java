package com.example.paytm.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WalletModel {
    @Id
    private long phone;
    private int balance;

    public WalletModel() {
    }

    public WalletModel(long phone, int balance) {
        this.phone = phone;
        this.balance = balance;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //
//    public void changeBalance(Integer amount) {
//        this.balance += amount;
//    }
}
