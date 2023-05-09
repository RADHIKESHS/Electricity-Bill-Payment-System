package com.masaischool.cw_project.Services;

import com.masaischool.cw_project.dao.TransactionDao;
import com.masaischool.cw_project.dao.TransactionDaoImpl;
import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;


public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;
    private BillService billService;

    public TransactionServiceImpl() {
        transactionDao = new TransactionDaoImpl();
        billService = new BillServiceImpl();
    }

    @SuppressWarnings("unused")
	@Override
    public Transaction createTransaction(Long consumerId, int billId, double transactionAmount) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException {
        Bill billToPay = billService.getBillById(billId);
        Consumer consumer = billToPay.getConsumer();

        Transaction transaction = new Transaction(billToPay, transactionAmount, false);
        Transaction savedTransaction = transactionDao.addTransaction(transaction);

        double outstandingAmount = billToPay.getTotalBill() - billToPay.getPaidAmount() - transactionAmount;

        if (outstandingAmount <= 0) {
            billService.updateBillStatus(billId, transactionAmount, Bill.Status.PAID);
        } else {
            billService.updateBillStatus(billId, transactionAmount, Bill.Status.PENDING);
        }

        return savedTransaction;
    }


}


