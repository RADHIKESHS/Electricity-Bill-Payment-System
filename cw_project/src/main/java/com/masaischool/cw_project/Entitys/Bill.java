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
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(Consumer consumer, LocalDate billMonth, double fixedCharge, int unitsConsumed, double taxes,
			double adjustment, double totalBill, String status, boolean isDeleted) {
		super();
		this.consumer = consumer;
		this.billMonth = billMonth;
		this.fixedCharge = fixedCharge;
		this.unitsConsumed = unitsConsumed;
		this.taxes = taxes;
		this.adjustment = adjustment;
		this.totalBill = totalBill;
		this.status = status;
		this.isDeleted = isDeleted;
	}
    
    
}

