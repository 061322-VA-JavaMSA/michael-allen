package com.revature.scanner;

import java.util.Scanner;
import java.util.Random;

public class ScannerDriver {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
        scan.close();
    }

    public static void loginScreen() {
        String username = "admin";
        String password = "pass";

        System.out.println("Please enter your username: ");
        String usernameInput = scan.nextLine();
        System.out.println("Please enter your password: ");
        String passwordInput = scan.nextLine();

        if(username.equals(usernameInput) && password.equals(passwordInput)) {
            System.out.println("Welcome!");
        }
        else {
            System.out.println("Invalid credentials.");
        }
    }

    public static void menu() {

        boolean showMenu = true;

        do {
            System.out.println("Select an option");
            System.out.println("1: Generate a random number between 1 and 10");
            System.out.println("2: Reverse a string");
            System.out.println("3: Exit");
            int option = scan.nextInt();
            scan.nextLine();

            switch(option) {
                case 1:
                    Random randNum = new Random();
                    int x = randNum.nextInt(10)+1;
                    System.out.println("Your random number is: " + x + "\n");
                    break;
                case 2:
                    System.out.println("\nType a string: ");
                    String input = scan.nextLine();
                    StringBuilder sb = new StringBuilder();
                    sb.append(input);
                    sb.reverse();
                    System.out.println("\nYour reversed string is: " + sb.toString() + "\n");
                    break;
                case 3:
                    showMenu = false;
                    break;
                default:
                    System.out.println("That is not a valid option.\n");
                    break;
            }
        } while(showMenu == true);

    }

}
