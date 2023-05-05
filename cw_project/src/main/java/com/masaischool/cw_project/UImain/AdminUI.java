//package com.masaischool.cw_project.UImain;
//
//import java.util.Scanner;
//
//public class AdminUI {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ElectricityBillService billService = new ElectricityBillService();
//        ConsumerService consumerService = new ConsumerService();
//
//        // Login
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//        Admin admin = new Admin(username, password);
//        if (!adminService.login(admin)) {
//            System.out.println("Invalid username or password. Exiting...");
//            System.exit(1);
//        }
//
//        // Menu
//        boolean exit = false;
//        while (!exit) {
//            System.out.println("\n=== ADMIN MENU ===");
//            System.out.println("1. View Consumers");
//            System.out.println("2. View Bills");
//            System.out.println("3. Delete Consumer");
//            System.out.println("4. Exit");
//            System.out.print("Enter choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    // View Consumers
//                    List<Consumer> consumers = consumerService.getAllConsumers();
//                    System.out.println("\n=== CONSUMERS ===");
//                    for (Consumer consumer : consumers) {
//                        System.out.println(consumer);
//                    }
//                    break;
//                case 2:
//                    // View Bills
//                    List<ElectricityBill> bills = billService.getAllBills();
//                    System.out.println("\n=== BILLS ===");
//                    for (ElectricityBill bill : bills) {
//                        System.out.println(bill);
//                    }
//                    break;
//                case 3:
//                    // Delete Consumer
//                    System.out.print("Enter consumer ID to delete: ");
//                    long consumerId = scanner.nextLong();
//                    scanner.nextLine();
//                    consumerService.deleteConsumer(consumerId);
//                    System.out.println("Consumer with ID " + consumerId + " deleted successfully.");
//                    break;
//                case 4:
//                    // Exit
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
//            }
//        }
//        scanner.close();
//    }
//}
//
