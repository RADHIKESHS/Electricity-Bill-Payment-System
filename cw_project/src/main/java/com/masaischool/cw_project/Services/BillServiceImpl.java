package com.masaischool.cw_project.Services;

import java.util.ArrayList;
import java.util.List;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Bill.Status;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import com.masaischool.cw_project.dao.BillDao;
import com.masaischool.cw_project.dao.BillDaoImpl;

public class BillServiceImpl implements BillService {

    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    @Override
    public Bill getBillsByConsumer(Long consumerId) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException {
        return billDao.getBillsByConsumer(consumerId);
    }

    @Override
    public Bill getPendingBillsByConsumer(Long consumerId) throws ClassNotFoundException, SomethingWentWrongException {
        return billDao.getPendingBillsByConsumer(consumerId);
    }

    @Override
    public Bill getBillById(long billId) throws ClassNotFoundException, SomethingWentWrongException {
        return billDao.getBillById(billId);
    }


    @Override
    public void updateBillStatus(int billId, double transactionAmount, Status paid) throws SomethingWentWrongException, BillNotFoundException, ClassNotFoundException {
        Bill bill = billDao.getBillById(billId);
        double totalBillAmount = bill.getTotalBill();
        double remainingAmount = totalBillAmount - transactionAmount;

        if (remainingAmount == 0) {
            bill.setStatus(paid);
            billDao.updateBill(bill);
        } else if (remainingAmount > 0 && bill.getStatus() != Status.PAID) {
            bill.setStatus(Status.PARTIALLY_PAID);
            bill.setTotalBill(remainingAmount);
            billDao.updateBill(bill);
        } else {
            throw new SomethingWentWrongException("Bill not paid successfully");
        }
    }



    @Override
    public List<Bill> getAllBills() throws SomethingWentWrongException, ClassNotFoundException {
        return billDao.getAllBills();
    }

    @Override
    public List<Bill> getAllPaidBills() throws SomethingWentWrongException, ClassNotFoundException {
        List<Bill> allBills = billDao.getAllBills();
        List<Bill> pendingBills = new ArrayList<>();
        for (Bill bill : allBills) {
            if (bill.getStatus() == Status.PAID) {
                pendingBills.add(bill);
            }
        }
        return pendingBills;
    }

    @Override
    public List<Bill> getAllPendingBills() throws SomethingWentWrongException, ClassNotFoundException {
        List<Bill> allBills = billDao.getAllBills();
        List<Bill> pendingBills = new ArrayList<>();
        for (Bill bill : allBills) {
            if (bill.getStatus() != Status.PAID) {
                pendingBills.add(bill);
            }
            
        }
        return pendingBills;
    }



    @Override
    public boolean createBill(Bill newBill) throws SomethingWentWrongException {
        return billDao.createBill(newBill);
    }

    @Override
    public boolean updateBill(Bill billToUpdate) throws SomethingWentWrongException {
        return billDao.updateBill2(billToUpdate);
    }

    @Override
    public boolean deleteBill(Long id) throws SomethingWentWrongException, BillNotFoundException {
        return billDao.deleteBill(id);
    }

    @Override
    public void updateBillTotalAmount(int billId, double remainingBillAmount) throws SomethingWentWrongException, BillNotFoundException, ClassNotFoundException {
        Bill bill = billDao.getBillById(billId);
        bill.setTotalBill(remainingBillAmount);
        billDao.updateBill(bill);
    }



}
