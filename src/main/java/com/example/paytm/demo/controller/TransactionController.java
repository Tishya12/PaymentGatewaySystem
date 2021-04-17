package com.example.paytm.demo.controller;

import com.example.paytm.demo.helpers.UtilityMethods;
import com.example.paytm.demo.model.TransactionModel;
import com.example.paytm.demo.model.WalletModel;
import com.example.paytm.demo.service.TransactionService;
import com.example.paytm.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    WalletService walletService;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping(value = "/transaction/all")
    public List<TransactionModel> displayAll(){
        logger.log(Level.INFO, "List of all transaction recieved at "+ UtilityMethods.get_current_time());
        return transactionService.displayall();
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<TransactionModel>> getAllTransactions(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "1") Integer pageSize) {
        List<TransactionModel> list = transactionService.getAllTransactions(pageNo, pageSize);

        return new ResponseEntity<List<TransactionModel>>(list, new HttpHeaders(), HttpStatus.OK);
    }



    @PostMapping(value = "/transaction")           // post mapping
    public String addTransaction(@RequestBody TransactionModel transactionModel) {

        System.out.println("where is the issue");
        List<WalletModel> sender_phone = walletService.findByPhone(transactionModel.getPayeephone());
        List<WalletModel> receiver_phone = walletService.findByPhone(transactionModel.getPayerphone());
        if(!sender_phone.isEmpty() && !receiver_phone.isEmpty()) {
            if(sender_phone.get(0).getBalance() >= transactionModel.getAmount()) {
                sender_phone.get(0).changeBalance(-transactionModel.getAmount()); // editing summoned objects
                receiver_phone.get(0).changeBalance(transactionModel.getAmount());
                walletService.addWallet(sender_phone.get(0));    //saving back the data
                walletService.addWallet(receiver_phone.get(0));
                transactionService.addtransaction(transactionModel);
                logger.log(Level.INFO, "Transaction done at "+ UtilityMethods.get_current_time());
               return "transaction successful and saved in the database";

            }
            else return "transaction failed due to insufficient balance";
        }
        else return "transaction failed due to wrong phone number";

    }
    @GetMapping(value = "/transaction/{transactionid}")
    public String transactionStatus(@PathVariable int transactionid){
        TransactionModel transactionModel = transactionService.findByTransactionid(transactionid);
        if(transactionModel==null)
            return "failed";
        else return "Successfull";
    }



}
