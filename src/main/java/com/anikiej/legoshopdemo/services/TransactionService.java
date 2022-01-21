package com.anikiej.legoshopdemo.services;

import com.anikiej.legoshopdemo.models.*;
import com.anikiej.legoshopdemo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction setExampleTransactions() {
        LegoSet set1 = new LegoSet(null, Theme.CLASSIC, "Creative Blue Bricks", 24.29, Currency.PLN, 52, AgeLimit.FOURYEARSPLUS, 100);
        LegoSet set2 = new LegoSet(null, Theme.CLASSIC, "Creative Transparent Bricks", 24.99, Currency.GBP, 500, AgeLimit.FOURYEARSPLUS, 200);
        LegoSet set3 = new LegoSet(null, Theme.CITY, "Hospital", 122.09, Currency.USD, 816, AgeLimit.SIXYEARSPLUS, 150);
        Transaction transaction1 = new Transaction(null, LocalDateTime.now(), 200, Currency.EUR, List.of(set1, set2, set3), true, true, true);
        return transactionRepository.save(transaction1);
    }

}
