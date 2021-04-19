package com.example.paytm.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(indexName = "elastic", indexStoreType = "transaction", shards = 2)
public class EStransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionid;

    private long senderphone;

    private long receiverphone;

    private Integer amount;

    public EStransactionModel() {
        super();
    }

    public EStransactionModel(String transactionid, long senderphone, long receiverphone, Integer amount) {
        this.transactionid = transactionid;
        this.senderphone = senderphone;
        this.receiverphone = receiverphone;
        this.amount = amount;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public long getSenderphone() {
        return senderphone;
    }

    public void setSenderphone(long senderphone) {
        this.senderphone = senderphone;
    }

    public long getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(long receiverphone) {
        this.receiverphone = receiverphone;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
