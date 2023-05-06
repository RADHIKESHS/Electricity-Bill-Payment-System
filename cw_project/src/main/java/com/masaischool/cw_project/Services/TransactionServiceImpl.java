package com.masaischool.cw_project.Services;

import com.masaischool.cw_project.dao.TransactionDao;
import com.masaischool.cw_project.dao.TransactionDaoImpl;
import com.masaischool.cw_project.model.Transaction;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    public TransactionServiceImpl() {
        transactionDao = new TransactionDaoImpl();
    }

    @Override
    public Transaction createTransaction(int consumerId, int billId, double transactionAmount) {
        Transaction transaction = new Transaction(consumerId, billId, transactionAmount);
        return transactionDao.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByConsumer(int consumerId) {
        return transactionDao.getTransactionsByConsumer(consumerId);
    }
}

