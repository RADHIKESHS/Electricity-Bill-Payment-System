package com.masaischool.cw_project.Services;

import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface TransactionService {
	Transaction createTransaction(Long consumerId, int billId, double transactionAmount) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException;
}

