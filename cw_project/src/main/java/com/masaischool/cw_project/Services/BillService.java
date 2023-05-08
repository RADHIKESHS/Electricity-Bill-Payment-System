package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Bill.Status;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface BillService {
    Bill getBillsByConsumer(Long consumerId) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException;
    Bill getPendingBillsByConsumer(Long consumerId) throws ClassNotFoundException, SomethingWentWrongException;
    Bill getBillById(long billId) throws ClassNotFoundException, SomethingWentWrongException;
	void updateBillStatus(int billId, double transactionAmount, Status paid) throws SomethingWentWrongException, BillNotFoundException, ClassNotFoundException;
	List<Bill> getAllBills() throws SomethingWentWrongException, ClassNotFoundException;
	List<Bill> getAllPaidBills() throws SomethingWentWrongException, ClassNotFoundException;
	List<Bill> getAllPendingBills() throws SomethingWentWrongException, ClassNotFoundException;
	boolean createBill(Bill newBill) throws SomethingWentWrongException;
	boolean updateBill(Bill billToUpdate) throws SomethingWentWrongException;
	boolean deleteBill(Long id) throws SomethingWentWrongException, BillNotFoundException;
	void updateBillTotalAmount(int billId, double remainingBillAmount) throws SomethingWentWrongException, BillNotFoundException, ClassNotFoundException;
}

