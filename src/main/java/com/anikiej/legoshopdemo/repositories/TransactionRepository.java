package com.anikiej.legoshopdemo.repositories;

import com.anikiej.legoshopdemo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
