package com.mycompany.loginapp;

import java.util.Scanner;

public class LoginApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== REGISTER ===");
        System.out.print("Enter username (with _ and <=5 chars): ");
        String username = sc.nextLine();

        System.out.print("Enter password (>=8 chars, Capital, number, special): ");
        String password = sc.nextLine();

        System.out.print("Enter SA cell (+27...): ");
        String cell = sc.nextLine();

        String regResult = login.registerUser(username, password, cell);
        System.out.println(regResult);
        
        if (!regResult.equals("User registered successfully.")) {
            return;
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String enteredUser = sc.nextLine();
        System.out.print("Enter password: ");
        String enteredPass = sc.nextLine();

        boolean loggedIn = login.loginUser(enteredUser, enteredPass, username, password);
        System.out.println(login.returnLoginStatus(loggedIn));
    }
}
