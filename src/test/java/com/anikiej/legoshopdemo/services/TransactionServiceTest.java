package com.anikiej.legoshopdemo.services;

import com.anikiej.legoshopdemo.models.*;
import com.anikiej.legoshopdemo.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;


    @Test
    void shouldGetEmptyTransaction() {
        Transaction transaction = new Transaction(null, LocalDateTime.of(2022, 1, 1, 0, 0), 0, Currency.GBP, null, false);
        assertThat(transactionService.getEmptyTransaction()).usingRecursiveComparison().isEqualTo(transaction);
    }

    @Test
    void shouldGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction(1L, LocalDateTime.now(), 0, Currency.GBP, null, false, false, false);
        transactions.add(transaction);
        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> allTransactions = transactionService.getAllTransactions();

        assertThat(allTransactions).hasSize(transactions.size());
    }

    @Test
    void shouldGetTransactionById() {
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(new Transaction()));
        Transaction transaction = transactionService.getTransactionById(1L);
        assertThat(transaction).isNotNull();
    }

    @Test
    void shouldNotGetTransactionById() {
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.empty());
        Transaction transaction = transactionService.getTransactionById(10L);
        assertThat(transaction).isNull();
    }

    @Test
    void shouldApplyDiscountInPercent () {
        Transaction sampleTransaction = new Transaction(1L, LocalDateTime.of(2022, 1, 1, 0, 0), 100, Currency.GBP, null, false, false, false);
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(sampleTransaction));

        Transaction transaction = transactionService.applyDiscountInPercent(10, 1L);
        assertThat(transaction.getValue()).isEqualTo(90.0);
    }

    @Test
    void shouldPayForTransaction () {
        Transaction sampleTransaction = new Transaction(1L, LocalDateTime.of(2022, 1, 1, 0, 0), 100, Currency.GBP, null, false, false, false);
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(sampleTransaction));

        Transaction transaction = transactionService.payForTransaction(1L);
        assertThat(transaction.isPaid()).isEqualTo(true);
    }

    @Test
    void shouldSendTransaction () {
        Transaction sampleTransaction = new Transaction(1L, LocalDateTime.of(2022, 1, 1, 0, 0), 100, Currency.GBP, null, false, false, false);
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(sampleTransaction));

        Transaction transaction = transactionService.sendTransaction(1L);
        assertThat(transaction.isSent()).isEqualTo(true);
    }

    @Test
    void shouldIsTransactionDelivered () {
        Transaction sampleTransaction = new Transaction(1L, LocalDateTime.of(2022, 1, 1, 0, 0), 100, Currency.GBP, null, false, false, false);
        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.of(sampleTransaction));

        boolean isDelivered = transactionService.isTransactionDelivered(1L);
        assertThat(isDelivered).isEqualTo(false);
    }

    @Test
    void shouldTransactionExistsById () {
        Mockito.when(transactionRepository.existsById(any())).thenReturn(true);

        boolean exists = transactionService.transactionExistsById(1L);
        assertThat(exists).isEqualTo(true);
    }

    @Test
    void shouldNotTransactionExistsById () {
        Mockito.when(transactionRepository.existsById(any())).thenReturn(false);

        boolean exists = transactionService.transactionExistsById(1L);
        assertThat(exists).isEqualTo(false);
    }

    @Test
    void shouldDeleteTransactionById () {
        Mockito.doNothing().when(transactionRepository).deleteById(any());

        transactionService.deleteTransactionById(1L);
        verify(transactionRepository, times(1)).deleteById(1L);
    }

}
