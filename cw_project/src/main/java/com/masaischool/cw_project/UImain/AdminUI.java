package com.masaischool.cw_project.UImain;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import com.masaischool.cw_project.Services.BillService;
import com.masaischool.cw_project.Services.BillServiceImpl;
import com.masaischool.cw_project.Services.ComplaintService;
import com.masaischool.cw_project.Services.ComplaintServiceImpl;
import com.masaischool.cw_project.Services.ConsumerService;
import com.masaischool.cw_project.Services.ConsumerServiceImpl;

public class AdminUI {

    private static Scanner scanner = new Scanner(System.in);
    private static ConsumerService consumerService = new ConsumerServiceImpl();
    private static BillService billService = new BillServiceImpl();
    private static ComplaintService complaintService = new ComplaintServiceImpl();
    static long consumerId;
    
    
    static void ViewConsumers() {
        // View Consumers
        try {
			for (Consumer consumer : consumerService.getAllConsumers()) {
			    System.out.println(consumer);
			}
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    static void ViewBills() {
        // View Bills  
        try {
			for (Bill bill : billService.getAllBills()) {
			    System.out.println(bill);
			}
		} catch (ClassNotFoundException | SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    static void ViewConsumerBill(Scanner scanner) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException {
        // View a Consumer's Bill
        System.out.print("Enter consumer ID: ");
        consumerId = scanner.nextLong();
        scanner.nextLine();
        Consumer billConsumer = consumerService.getConsumerById(consumerId);
        if (billConsumer != null) {
            Bill consumerBill = billService.getBillsByConsumer(consumerId);
            if (consumerBill != null) {
                System.out.println(consumerBill);
            } else {
                System.out.println("No bill found for the consumer with ID " + consumerId);
            }
        } else {
            System.out.println("No consumer found with ID " + consumerId);
        }
    }
    
    static void ViewPaid_and_PendingBills(Scanner scanner) throws ClassNotFoundException, SomethingWentWrongException {
        // View Paid and Pending Bills 
        System.out.println("1. View Paid Bills");
        System.out.println("2. View Pending Bills");
        System.out.print("Enter your choice: ");
        int billChoice = scanner.nextInt();
        scanner.nextLine();
        switch (billChoice) {
            case 1:
                // View Paid Bills
                for (Bill bill : billService.getAllPaidBills()) {
                    System.out.println(bill);
                }
                break;
            case 2:
                // View Pending Bills
                for (Bill bill : billService.getAllPendingBills()) {
                    System.out.println(bill);
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
    
    
    static void CreateBill(Scanner scanner) throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException {
        try {
            System.out.print("Enter consumer ID: ");
            long consumerId = scanner.nextLong();
            scanner.nextLine();
            Consumer consumer = consumerService.getConsumerById(consumerId);

            Bill existingBill = billService.getBillsByConsumer(consumerId);
            if (existingBill != null) {
                if (existingBill.getStatus() != Bill.Status.PAID) {
                    // If an existing bill exists and it has a status of "Pending," update the bill with new units consumed and total amount.
                    System.out.println("An existing bill with ID " + existingBill.getId() + " already exists and has a status of 'Pending.'");
                    System.out.print("Enter units consumed: ");
                    int unitsConsumed = scanner.nextInt();
                    scanner.nextLine();
                    double taxes = unitsConsumed * 0.1;
                    double adjustment = 0.0;
                    double totalBill = existingBill.getFixedCharge() + (unitsConsumed * 5) + taxes + adjustment;
                    existingBill.setUnitsConsumed(unitsConsumed);
                    existingBill.setTaxes(taxes);
                    existingBill.setAdjustment(adjustment);
                    existingBill.setTotalBill(totalBill);

                    if (billService.updateBill(existingBill)) {
                        System.out.println("Bill updated successfully");
                    } else {
                        System.out.println("Failed to update bill");
                    }
                } else if (existingBill.getStatus() == Bill.Status.PAID) {
                    // If an existing bill exists and it has a status of "Paid," delete the bill and create a new bill.
                    if (billService.deleteBill(existingBill.getId())) {
                        System.out.println("Bill with ID " + existingBill.getId() + " has been deleted.");
                        createNewBill(scanner, consumer);
                    } else {
                        System.out.println("Failed to delete bill with ID " + existingBill.getId());
                    }
                }
            } else {
                // Create a new bill.
                createNewBill(scanner, consumer);
            }
        } catch (SomethingWentWrongException e) {
            System.out.println("Unable to create or update Bill");
        }
    }

    static void createNewBill(Scanner scanner, Consumer consumer) throws SomethingWentWrongException {
        System.out.print("Enter units consumed: ");
        int unitsConsumed = scanner.nextInt();
        scanner.nextLine();
        double fixedCharge = 50.0;
        double taxes = unitsConsumed * 0.1;
        double adjustment = 0.0;
        double totalBill = fixedCharge + (unitsConsumed * 5) + taxes + adjustment;
        double paidAmount = 0.0;
        Bill.Status status = Bill.Status.PENDING;
        boolean isDeleted = false;
        Bill newBill = new Bill(consumer, LocalDate.now(), fixedCharge, unitsConsumed, taxes, adjustment, totalBill, paidAmount, status, isDeleted);
        if (billService.createBill(newBill)) {
            System.out.println("Bill created successfully");
        } else {
            System.out.println("Failed to create bill");
        }
    }


    static void UpdateBill(Scanner scanner) throws ClassNotFoundException, SomethingWentWrongException {
        // Update Bill
        System.out.print("Enter bill ID: ");
        long billId = scanner.nextLong();
        scanner.nextLine();
        Bill billToUpdate = billService.getBillById(billId);
        if (billToUpdate != null) {
            System.out.print("Enter new units consumed: ");
            int newUnitsConsumed = scanner.nextInt();
            scanner.nextLine();
            double newTotalAmount = billToUpdate.getTotalBill() + (newUnitsConsumed - billToUpdate.getUnitsConsumed()) * 5.0;
            billToUpdate.setUnitsConsumed(newUnitsConsumed);
            billToUpdate.setTotalBill(newTotalAmount);
            if (billService.updateBill(billToUpdate)) {
                System.out.println("Bill updated successfully");
            } else {
                System.out.println("Failed to update bill");
            }
        } else {
            System.out.println("No bill found with ID " + billId);
        }
    }
    
    static void ViewComplaints() {
        // View Complaints
        try {
			for (Complaint complaint : complaintService.getAllComplaints()) {
			    System.out.println(complaint);
			}
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
    
    static void UpdateComplaintStatus(Scanner scanner) throws SomethingWentWrongException {
    	// Update Complaint Status
    	System.out.print("Enter consumer ID: ");
    	long complaintId = scanner.nextLong();
    	scanner.nextLine();
    	List<Complaint> complaintsToUpdate = complaintService.getComplaintsByConsumer(complaintId);
    	if (!complaintsToUpdate.isEmpty()) {
    	    for (Complaint complaintToUpdate : complaintsToUpdate) {
    	        System.out.println("Current status: " + complaintToUpdate.getStatus());
    	        System.out.print("Enter new status: ");
    	        String newStatus = scanner.nextLine();
    	        Complaint.ComplaintStatus status = Complaint.ComplaintStatus.valueOf(newStatus.toUpperCase());
    	        complaintToUpdate.setStatus(status);
    	        if (complaintService.updateComplaint(complaintToUpdate)) {
    	            System.out.println("Complaint status updated successfully");
    	        } else {
    	            System.out.println("Failed to update complaint status");
    	        }
    	    }
    	} else {
    	    System.out.println("No complaint found with ID " + complaintId);
    	}
    }

    public static void adminLogin() throws ClassNotFoundException, SomethingWentWrongException, BillNotFoundException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter your login ID: ");
            String adminId = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            if (adminId.equals("admin") && password.equals("admin")) {
                System.out.println("Login successful");
                int choice = 0;
                while (choice != 9) {
                	System.out.println("+---------------------------------------+");
                	System.out.println("|   Enter your choice:                  |");
                	System.out.println("+---------------------------------------+");
                    System.out.println("|   1. View Consumers                   |");
                    System.out.println("|   2. View Bills                       |");
                    System.out.println("|   3. View a Consumer's Bill           |");
                    System.out.println("|   4. View Paid and Pending Bills      |");
                    System.out.println("|   5. Create Bill                      |");
                    System.out.println("|   6. Update Bill                      |");
                    System.out.println("|   7. View Complaints                  |");
                    System.out.println("|   8. Update Complaint Status          |");
                    System.out.println("|   9. Exit                             |");
                    System.out.println("+---------------------------------------+");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                        	ViewConsumers();
                            break;
                        case 2:
                        	ViewBills();
                            break;
                        case 3:
                        	ViewConsumerBill(scanner);
                            break;
                        case 4:
                        	ViewPaid_and_PendingBills(scanner);
                            break;
                        case 5:
                        	CreateBill(scanner);
                            break;
                        case 6:
                        	UpdateBill(scanner);
                            break;
                        case 7:
                        	ViewComplaints();
                            break;
                        case 8:
                        	UpdateComplaintStatus(scanner);
                        	break;
                        case 9:                      	
                            // Exit
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid login ID or password");
            }
        }
    }
}
                                
                               


