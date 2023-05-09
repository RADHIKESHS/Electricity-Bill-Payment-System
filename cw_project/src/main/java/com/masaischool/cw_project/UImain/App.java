package com.masaischool.cw_project.UImain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        
        int choice = 0;
        while (choice != 3) {
        	System.out.println("+ --------------------------------+");
        	System.out.println("|     <--    Welcome    -->       |");
        	System.out.println("|---------------------------------|");
        	System.out.println("|   Select Login Type:            |");
        	System.out.println("|---------------------------------|");
        	System.out.println("|   1. Admin Login                |");
        	System.out.println("|   2. User Login                 |");
        	System.out.println("|   3. Exit                       |");
        	System.out.println("+---------------------------------+");
           
           try {
              choice = scanner.nextInt();
              
              switch (choice) {
                 case 1:
                    AdminUI.adminLogin();
                    break;
                 case 2:
                    ConsumerUI.consumerLogin();
                    break;
                 case 3:
                    System.out.println("Exiting Program...");
                    break;
                 default:
                    System.out.println("Invalid Choice. Try Again.");
                    break;
              }
           } catch (InputMismatchException e) {
              System.out.println("Invalid Input. Please enter a number.");
              scanner.next();
           }
        }
        
        scanner.close();
     }
    
}
