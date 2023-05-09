package com.masaischool.cw_project.UImain;

import java.util.Scanner;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import com.masaischool.cw_project.Services.BillService;
import com.masaischool.cw_project.Services.BillServiceImpl;
import com.masaischool.cw_project.Services.ComplaintService;
import com.masaischool.cw_project.Services.ComplaintServiceImpl;
import com.masaischool.cw_project.Services.ConsumerService;
import com.masaischool.cw_project.Services.ConsumerServiceImpl;
import com.masaischool.cw_project.Services.TransactionService;
import com.masaischool.cw_project.Services.TransactionServiceImpl;


public class ConsumerUI{
    static ConsumerService consumerService = new ConsumerServiceImpl();
    static BillService billService = new BillServiceImpl();
    static TransactionService transactionService = new TransactionServiceImpl();
    static ComplaintService complaintService = new ComplaintServiceImpl();
    static Consumer consumer = null;
	
	static void SignUpConsumer(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Mobile Number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        try {
			consumer = consumerService.createConsumer(firstName, lastName, username, password, address, mobileNumber, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
        System.out.println("Sign up successful. Your consumer id is " + consumer.getId());
	}
	
	
	static void LogIn(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter Username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        try {
			consumer = consumerService.login(loginUsername, loginPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

        if (consumer != null) {
        	System.out.println("+-----------------------------------------------------------------------+");
            System.out.println("|   ## Login successful ## <--   Welcome  " + consumer.getFirstName() + " " + consumer.getLastName()+"  -->");
            System.out.println("+-----------------------------------------------------------------------+");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
	}
	
	
	static void ViewProfile() {
        System.out.println("Name: " + consumer.getFirstName() + " " + consumer.getLastName());
        System.out.println("Address: " + consumer.getAddress());
        System.out.println("Mobile Number: " + consumer.getMobileNumber());
        System.out.println("Email: " + consumer.getEmail());
	}
	
	public static void ViewBills() throws ClassNotFoundException, BillNotFoundException, SomethingWentWrongException {
	    System.out.println("Bills: ");
	    Bill cbill = billService.getBillsByConsumer(consumer.getId()); 
	    if(cbill==null) {
	        System.out.println("There is no bill");
	    } else {
	        System.out.println(cbill.getId() + ". " + cbill.getBillMonth() + " - " + cbill.getStatus());
	    }
	}


	static void PayBill(Scanner scanner) {
	    try {
	        Bill pendingBill = billService.getPendingBillsByConsumer(consumer.getId());
	        if (pendingBill == null) {
	            System.out.println("No pending bills.");
	            return;
	        }

	        System.out.println("Pending Bill: ");
	        System.out.println(pendingBill.getId() + ". " + pendingBill.getBillMonth() + " - " + pendingBill.getTotalBill());

	        System.out.print("Enter the bill id you want to pay: ");
	        int billId = scanner.nextInt();
	        scanner.nextLine();

	        if (billId != pendingBill.getId()) {
	            System.out.println("Invalid bill id or selected bill cannot be paid. Please try again.");
	            return;
	        }

	        System.out.print("Enter the transaction amount: ");
	        double transactionAmount = scanner.nextDouble();
	        scanner.nextLine();

	        if (transactionAmount <= 0 || transactionAmount > pendingBill.getTotalBill()) {
	            System.out.println("Invalid transaction amount.");
	            return;
	        }

	        double remainingBillAmount = pendingBill.getTotalBill() - transactionAmount;
	        if (remainingBillAmount == 0) {
	            billService.updateBillStatus(billId, transactionAmount, Bill.Status.PAID);
	        } else if (remainingBillAmount > 0) {
	            billService.updateBillStatus(billId, transactionAmount, Bill.Status.PARTIALLY_PAID);
	            billService.updateBillTotalAmount(billId, remainingBillAmount);
	        }

	        Transaction transaction = transactionService.createTransaction(consumer.getId(), billId, transactionAmount);
	        System.out.println("Transaction successful. Transaction id: " + transaction.getId());
	    } catch (ClassNotFoundException | SomethingWentWrongException | BillNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	
	static void FileComplaint(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter the complaint description: ");
        String complaintDescription = scanner.nextLine();
        System.out.print("Enter the complaint type (Billing, Connection, Others): ");
        String complaintType = scanner.next();

        Complaint complaint = null;
		try {
			complaint = complaintService.createComplaint(consumer.getId(), complaintDescription, complaintType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Complaint filed successfully. Complaint id: " + complaint.getId());
	}
	
    public static void consumerLogin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;

        while (exit) {
        	System.out.println("+-----------------------------------------------------------------------+");
            System.out.println("|           Welcome to the Electricity Bill Payment System              |");
        	System.out.println("+-----------------------------------------------------------------------+");
            System.out.println("|    1. Sign Up                                                         |");
            System.out.println("|    2. Login                                                           |");
            System.out.println("|    3. Exit                                                            |");
        	System.out.println("+-----------------------------------------------------------------------+");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	SignUpConsumer( scanner);
                    break;

                case 2:
                	LogIn(scanner);
                    break;

                case 3:
                    exit = false;
                    System.out.println("Thank you ,  See you again ");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (consumer != null) {
                boolean loggedIn = true;

                while (loggedIn) {
                	System.out.println("+---------------------------------------+");
                	System.out.println("|    Enter your choice:                 |");
                	System.out.println("+---------------------------------------+");
                    System.out.println("|    1. View Profile                    |");
                    System.out.println("|    2. View Bills                      |");
                    System.out.println("|    3. Pay Bill                        |");
                    System.out.println("|    4. File Complaint                  |");
                    System.out.println("|    5. Logout                          |");
                    System.out.println("+---------------------------------------+");

                    int consumerChoice = scanner.nextInt();

                    switch (consumerChoice) {
                        case 1:
                        	ViewProfile();
                            break;

                        case 2:
                        	ViewBills();
                            break;

                        case 3:
                        	PayBill(scanner);
                            break;

                        case 4:
                        	FileComplaint(scanner);
                            break;
                        case 5:
                            loggedIn = false;
                            System.out.println("Thanks you , Welcome for next time ");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }
}




