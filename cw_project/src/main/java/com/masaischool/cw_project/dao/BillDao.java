package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Bill.Status;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
public interface BillDao {
	Bill getBillsByConsumer(Long consumerId) throws SomethingWentWrongException, ClassNotFoundException, BillNotFoundException;
	Bill getPendingBillsByConsumer(Long consumerId) throws SomethingWentWrongException, ClassNotFoundException;
	Bill getBillById(long billId) throws SomethingWentWrongException, ClassNotFoundException;
	void updateBill(Bill bill) throws SomethingWentWrongException;
	List<Bill> getAllBills() throws SomethingWentWrongException, ClassNotFoundException;
	List<Bill> getBillsByStatus(Status paid) throws SomethingWentWrongException, ClassNotFoundException;
	boolean createBill(Bill newBill) throws SomethingWentWrongException;
	boolean updateBill2(Bill billToUpdate) throws SomethingWentWrongException;
	boolean deleteBill(Long id) throws SomethingWentWrongException, BillNotFoundException;
}
