package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Transaction;

public interface TransactionService {
    Transaction createTransaction(int consumerId, int billId, double transactionAmount);
    List<Transaction> getTransactionsByConsumer(int consumerId);
}

