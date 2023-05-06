package com.masaischool.cw_project.UImain;

import java.util.Scanner;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Services.BillService;
import com.masaischool.cw_project.Services.BillServiceImpl;
import com.masaischool.cw_project.Services.ComplaintService;
import com.masaischool.cw_project.Services.ComplaintServiceImpl;
import com.masaischool.cw_project.Services.ConsumerService;
import com.masaischool.cw_project.Services.ConsumerServiceImpl;
import com.masaischool.cw_project.Services.TransactionService;
import com.masaischool.cw_project.Services.TransactionServiceImpl;


public class ConsumerUI{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsumerService consumerService = new ConsumerServiceImpl();
        BillService billService = new BillServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        ComplaintService complaintService = new ComplaintServiceImpl();
        Consumer consumer = null;
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Electricity Bill Payment System - Consumer Interface");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume the leftover newline
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

                    consumer = consumerService.createConsumer(firstName, lastName, username, password, address, mobileNumber, email);
                    System.out.println("Sign up successful. Your consumer id is " + consumer.getId());
                    break;

                case 2:
                    scanner.nextLine(); // Consume the leftover newline
                    System.out.print("Enter Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.nextLine();

                    consumer = consumerService.login(loginUsername, loginPassword);

                    if (consumer != null) {
                        System.out.println("Login successful. Welcome, " + consumer.getFirstName() + " " + consumer.getLastName());
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;

                case 3:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (consumer != null) {
                boolean loggedIn = true;

                while (loggedIn) {
                    System.out.println("1. View Profile");
                    System.out.println("2. View Bills");
                    System.out.println("3. Pay Bill");
                    System.out.println("4. View Transactions");
                    System.out.println("5. File Complaint");
                    System.out.println("6. Logout");

                    int consumerChoice = scanner.nextInt();

                    switch (consumerChoice) {
                        case 1:
                            System.out.println("Name: " + consumer.getFirstName() + " " + consumer.getLastName());
                            System.out.println("Address: " + consumer.getAddress());
                            System.out.println("Mobile Number: " + consumer.getMobileNumber());
                            System.out.println("Email: " + consumer.getEmail());
                            break;

                        case 2:
                            System.out.println("Bills: ");
                            for (Bill bill : billService.getBillsByConsumer(consumer.getId())) {
                                System.out.println(bill.getId() + ". " + bill.getBillMonth() + " - " + bill.getStatus());
                            }
                            break;

                        case 3:
                            System.out.println("Pending Bills: ");
                            for (Bill bill : billService.getPendingBillsByConsumer(consumer.getId())) {
                                System.out.println(bill.getId() + ". " + bill.getBillMonth() + " - " + bill.getTotalBill());
                            }
                            
                            
                            if (billToPay == null) {
                                System.out.println("Invalid bill id. Please try again.");
                            } else if (billToPay.getStatus() != BillStatus.PENDING) {
                                System.out.println("Selected bill cannot be paid. Please select a pending bill.");
                            } else {
                                System.out.print("Enter the transaction amount: ");
                                double transactionAmount = scanner.nextDouble();

                                Transaction transaction = transactionService.createTransaction(consumer.getId(), billId, transactionAmount);
                                billService.updateBillStatus(billId, transactionAmount);

                                System.out.println("Transaction successful. Transaction id: " + transaction.getId());
                            }
                            break;

                        case 4:
                            System.out.println("Transactions: ");
                            for (Transaction transaction : transactionService.getTransactionsByConsumer(consumer.getId())) {
                                System.out.println(transaction.getId() + ". Bill ID: " + transaction.getBillId() + " - " + transaction.getTransactionAmount() + " - " + transaction.getTransactionDate());
                            }
                            break;

                        case 5:
                            scanner.nextLine(); // Consume the leftover newline
                            System.out.print("Enter the complaint description: ");
                            String complaintDescription = scanner.nextLine();
                            System.out.print("Enter the complaint type (1 - Billing, 2 - Connection, 3 - Others): ");
                            int complaintType = scanner.nextInt();

                            Complaint complaint = complaintService.createComplaint(consumer.getId(), complaintDescription, ComplaintType.values()[complaintType-1]);

                            System.out.println("Complaint filed successfully. Complaint id: " + complaint.getId());
                            break;

                        case 6:
                            loggedIn = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }



