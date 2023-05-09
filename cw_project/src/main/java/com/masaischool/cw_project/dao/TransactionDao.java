package com.masaischool.cw_project.dao;

import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface TransactionDao {

	Transaction addTransaction(Transaction transaction) throws SomethingWentWrongException;

}
