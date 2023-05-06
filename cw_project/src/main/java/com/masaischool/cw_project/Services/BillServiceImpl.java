package com.masaischool.cw_project.Services;


import com.masaischool.cw_project.dao.BillDao;
import com.masaischool.cw_project.dao.BillDaoImpl;
//import com.masaischool.cw_project.model.Bill;
//import com.masaischool.cw_project.model.BillStatus;

import java.util.List;

public class BillServiceImpl implements BillService {

    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }

    @Override
    public List<Bill> getBillsByConsumer(int consumerId) {
        return billDao.getBillsByConsumer(consumerId);
    }

    @Override
    public List<Bill> getPendingBillsByConsumer(int consumerId) {
        return billDao.getPendingBillsByConsumer(consumerId);
    }

    @Override
    public Bill getBillById(int billId) {
        return billDao.getBillById(billId);
    }

    @Override
    public void updateBillStatus(int billId, double transactionAmount) {
        Bill bill = billDao.getBillById(billId);
        bill.setTotalPaid(bill.getTotalPaid() + transactionAmount);

        if (bill.getTotalPaid() == bill.getTotalBill()) {
            bill.setStatus(BillStatus.PAID);
        }

        billDao.updateBill(bill);
    }
}


