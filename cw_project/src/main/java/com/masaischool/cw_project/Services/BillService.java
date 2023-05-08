package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Bill;

public interface BillService {
    List<Bill> getBillsByConsumer(int consumerId);
    List<Bill> getPendingBillsByConsumer(int consumerId);
    Bill getBillById(int billId);
    boolean updateBillStatus(int billId, double transactionAmount);
}

