package com.example.paytm.demo.service;

import com.example.paytm.demo.model.TransactionModel;
import com.example.paytm.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transRepository;

    //saving transactions in the database
    public TransactionModel addtransaction(TransactionModel transModel) {
        return transRepository.save(transModel);
    }

//    method for displaying all the transaction in the database
    public List<TransactionModel> displayall() {
        return transRepository.findAll();
    }



    //returning object of type transmodel if the given transaction id is present
    public TransactionModel findByTransactionid(int transactionid) {
        return transRepository.findByTransactionid(transactionid);
    }

    //returning object of type transmodel if the given payerphone is present
    public List<TransactionModel> findbyPayerPhone(long payerphone) {
        return transRepository.findByPayerphone(payerphone);
    }

    //returning object of type transmodel if the given payeephone is present
    public List<TransactionModel> findbyPayeePhone(long payeephone) {
        return transRepository.findByPayeephone(payeephone);
    }

    public List<TransactionModel> getAllTransactions(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<TransactionModel> pagedResult = transRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<TransactionModel>();
        }
    }
}
