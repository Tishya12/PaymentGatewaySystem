package com.example.paytm.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransactionModel {
    @Id                                   //primary key
    private int transactionid;
    private long payerphone;
    private long payeephone;
    private int amount;

    public TransactionModel() {
   super();
    }

    public TransactionModel(int transactionid, long payerphone, long payeephone, int amount) {
        this.transactionid = transactionid;
        this.payerphone = payerphone;
        this.payeephone = payeephone;
        this.amount = amount;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public long getPayerphone() {
        return payerphone;
    }

    public void setPayerphone(long payerphone) {
        this.payerphone = payerphone;
    }

    public long getPayeephone() {
        return payeephone;
    }

    public void setPayeephone(long payeephone) {
        this.payeephone = payeephone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
