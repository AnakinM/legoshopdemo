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

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<Transaction> applyDiscountInPercent(@RequestParam Integer percent, @PathVariable("id") Long id) {
        Transaction transaction = transactionService.applyDiscountInPercent(percent, id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<Transaction> payForTransaction(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.payForTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}/send")
    public ResponseEntity<Transaction> sendTransaction(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.sendTransaction(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}/isdelivered")
    public ResponseEntity<Boolean> isTransactionDelivered(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.isTransactionDelivered(id));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> transactionExistsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.transactionExistsById(id));
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteTransactionById(@PathVariable("id") Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok("Deleted");
    }

}
