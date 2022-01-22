package com.anikiej.legoshopdemo.services;

import com.anikiej.legoshopdemo.models.*;
import com.anikiej.legoshopdemo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getExampleTransaction() {
        LegoSet set1 = new LegoSet(null, Theme.CLASSIC, "Creative Blue Bricks", 4.49, Currency.GBP, 52, AgeLimit.FOURYEARSPLUS, 3);
        LegoSet set2 = new LegoSet(null, Theme.CLASSIC, "Creative Transparent Bricks", 24.99, Currency.GBP, 500, AgeLimit.FOURYEARSPLUS, 2);
        LegoSet set3 = new LegoSet(null, Theme.CITY, "Hospital", 89.99, Currency.GBP, 816, AgeLimit.SIXYEARSPLUS, 1);
        double totalPrice = set1.getPrice() * set1.getQuantity()
                + set2.getPrice() * set2.getQuantity()
                + set3.getPrice() * set3.getQuantity();
        Transaction transaction1 = new Transaction(null, LocalDateTime.now(), totalPrice, Currency.GBP, List.of(set1, set2, set3), false, false, false);
        return transactionRepository.save(transaction1);
    }

    public Transaction getEmptyTransaction() {
        return new Transaction(null, LocalDateTime.of(2022, 1, 1, 0, 0), 0, Currency.GBP, null, false);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction applyDiscountInPercent(Integer percent, Long id) {
        Optional<Transaction> item = transactionRepository.findById(id);
        if (item.isPresent()) {
            Transaction transaction = item.get();
            double transactionValue = transaction.getValue();
            if (percent > 0 && percent <= 100 && transactionValue > 0) {
                double discountAmount = transactionValue * (percent.doubleValue() / 100.0);
                double discountedValue = transactionValue - discountAmount;
                transaction.setValue(discountedValue);
                transactionRepository.save(transaction);
                return transaction;
            }
        }
       return new Transaction();
    }

    public Transaction payForTransaction(Long id) {
        Optional<Transaction> item = transactionRepository.findById(id);
        if (item.isPresent()) {
            Transaction transaction = item.get();
            transaction.setPaid(true);
            transactionRepository.save(transaction);
            return transaction;
        }
        return new Transaction();
    }

    public Transaction sendTransaction(Long id) {
        Optional<Transaction> item = transactionRepository.findById(id);
        if (item.isPresent()) {
            Transaction transaction = item.get();
            transaction.setSent(true);
            transactionRepository.save(transaction);
            return transaction;
        }
        return new Transaction();
    }

    public boolean isTransactionDelivered(Long id) {
        Optional<Transaction> item = transactionRepository.findById(id);
        if (item.isPresent()) {
            Transaction transaction = item.get();
            return transaction.isDelivered();
        }
        return false;
    }

    public boolean transactionExistsById(Long id) {
        return transactionRepository.existsById(id);
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

}
