package com.example.paytm.demo.repository;

import com.example.paytm.demo.model.EStransactionModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ESTransactionRepository extends ElasticsearchRepository<EStransactionModel,String> {
    List<EStransactionModel> findBySenderphone(long phone);

    List<EStransactionModel> findByReceiverphone(long phone);
}
