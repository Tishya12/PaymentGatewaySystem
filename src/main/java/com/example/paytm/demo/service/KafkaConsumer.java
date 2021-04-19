package com.example.paytm.demo.service;

import com.example.paytm.demo.model.EStransactionModel;
import com.example.paytm.demo.repository.ESTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private ESTransactionRepository esTransactionRepository;

    @KafkaListener(topics = "txn_by_ids", groupId = "group_json",
            containerFactory = "concurrentKafkaListenerContainerFactory")          //listening(reading) from kafka by consumer
    public void consumeJson(EStransactionModel transaction) {
        esTransactionRepository.save(transaction); /////////////////////////for saving in elastic repository//////
        System.out.println("Consumed JSON Message: " + transaction);
    }
}
