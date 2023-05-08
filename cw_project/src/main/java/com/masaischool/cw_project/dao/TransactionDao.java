package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface TransactionDao {

	Transaction addTransaction(Transaction transaction) throws SomethingWentWrongException;

}
