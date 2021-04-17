package com.example.paytm.demo.repository;

import com.example.paytm.demo.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer>, PagingAndSortingRepository<TransactionModel,Integer> {
    public TransactionModel findByTransactionid(Integer transactionid);
    public List<TransactionModel> findByPayeephone(long payeephone);
    public List<TransactionModel> findByPayerphone(long payerphone);
    public List<TransactionModel> findAll();
}
