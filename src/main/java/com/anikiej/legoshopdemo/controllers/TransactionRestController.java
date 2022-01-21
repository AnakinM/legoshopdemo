package com.anikiej.legoshopdemo.controllers;

import com.anikiej.legoshopdemo.models.Transaction;
import com.anikiej.legoshopdemo.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/populate")
    public ResponseEntity<Transaction> setExampleTransactions() {
        return ResponseEntity.ok(transactionService.setExampleTransactions());
    }
}
