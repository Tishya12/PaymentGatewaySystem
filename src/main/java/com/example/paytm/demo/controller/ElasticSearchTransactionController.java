package com.example.paytm.demo.controller;

import com.example.paytm.demo.helpers.UtilityMethods;
import com.example.paytm.demo.model.EStransactionModel;
import com.example.paytm.demo.model.TransactionModel;
import com.example.paytm.demo.model.WalletModel;
import com.example.paytm.demo.repository.ESTransactionRepository;
import com.example.paytm.demo.service.TransactionService;
import com.example.paytm.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@RestController
public class ElasticSearchTransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    WalletService walletService;
    @Autowired
    private ESTransactionRepository elasticRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;
    String kafkaTopic = "txn_by_ids";


    @PostMapping(value = "/elastictransaction")           // post mapping
    public String addTransaction(@RequestBody EStransactionModel eStransactionModel) {

        System.out.println("where is the issue");
        List<WalletModel> sender_phone = walletService.findByPhone(eStransactionModel.getSenderphone());
        List<WalletModel> receiver_phone = walletService.findByPhone(eStransactionModel.getReceiverphone());
        if(!sender_phone.isEmpty() && !receiver_phone.isEmpty()) {
            if(sender_phone.get(0).getBalance() >= eStransactionModel.getAmount()) {
                sender_phone.get(0).changeBalance(-eStransactionModel.getAmount()); // editing summoned objects
                receiver_phone.get(0).changeBalance(eStransactionModel.getAmount());
                kafkaTemplate.send(kafkaTopic, eStransactionModel);
                walletService.addWallet(sender_phone.get(0));    //saving back the data
                walletService.addWallet(receiver_phone.get(0));

//                logger.log(Level.INFO, "Transaction done at "+ UtilityMethods.get_current_time());
                return "transaction successful and saved in the database";

            }
            else return "transaction failed due to insufficient balance";
        }
        else return "transaction failed due to wrong phone number";

    }

    //get mapping for serving transactions through elastic search
    @GetMapping("/elastictransaction/{phone}")
    public List<EStransactionModel> getTransactions(@PathVariable long phone) {
        List<EStransactionModel> sender = elasticRepository.findBySenderphone(phone);
        List<EStransactionModel> reciever = elasticRepository.findByReceiverphone(phone);

        List<EStransactionModel> newlist = new ArrayList<EStransactionModel>();
        newlist.addAll(sender);
        newlist.addAll(reciever);
        return newlist;
    }
}
