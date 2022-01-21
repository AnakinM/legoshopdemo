package com.anikiej.legoshopdemo.controllers;

import com.anikiej.legoshopdemo.models.Transaction;
import com.anikiej.legoshopdemo.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    private final TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/example")
    public ResponseEntity<Transaction> getExampleTransaction() {
        return ResponseEntity.ok(transactionService.getExampleTransaction());
    }

    @GetMapping("/empty")
    public ResponseEntity<Transaction> getEmptyTransaction() {
        return ResponseEntity.ok(transactionService.getEmptyTransaction());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<Transaction> applyDiscountInPercent(@RequestParam Integer percent, @PathVariable("id") Long id) {
        Transaction transaction = transactionService.applyDiscountInPercent(percent, id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<Transaction> applyDiscountInPercent(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.payForTransaction(id);
        return ResponseEntity.ok(transaction);
    }

}
