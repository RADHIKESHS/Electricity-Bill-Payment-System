package com.masaischool.cw_project.Entitys;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {

    public enum Status {
        PENDING,
        PARTIALLY_PAID,
        PAID,
        OVERDUE,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private Consumer consumer;

    @Column(name = "bill_month")
    private LocalDate billMonth;

    @Column(name = "fixed_charge")
    private double fixedCharge;

    @Column(name = "units_consumed")
    private int unitsConsumed;

    @Column(name = "taxes")
    private double taxes;

    @Column(name = "adjustment")
    private double adjustment;

    @Column(name = "total_bill")
    private double totalBill;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "status")
    private Status status;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public Bill() {
        super();
    }

    public Bill(Consumer consumer, LocalDate billMonth, double fixedCharge, int unitsConsumed, double taxes,
                double adjustment, double totalBill, double paidAmount, Status status, boolean isDeleted) {
        super();
        this.consumer = consumer;
        this.billMonth = billMonth;
        this.fixedCharge = fixedCharge;
        this.unitsConsumed = unitsConsumed;
        this.taxes = taxes;
        this.adjustment = adjustment;
        this.totalBill = totalBill;
        this.paidAmount = paidAmount;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public LocalDate getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(LocalDate billMonth) {
        this.billMonth = billMonth;
    }

    public double getFixedCharge() {
        return fixedCharge;
    }

    public void setFixedCharge(double fixedCharge) {
        this.fixedCharge = fixedCharge;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(double adjustment) {
        this.adjustment = adjustment;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

	@Override
	public String toString() {
		return "Bill id=" + id + ", ConsumerID=" + consumer.getId()+",  Consumer Name= "+consumer.getFirstName()+" "+consumer.getLastName() + ", billMonth=" + billMonth + ", unitsConsumed=" + unitsConsumed + ", taxes=" + taxes + ",totalBill=" + totalBill + ", paidAmount=" + paidAmount + ", status=" + status ;
	}
    
    
	
}

