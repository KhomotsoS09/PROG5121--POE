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

        System.out.print("Enter SA cell (+27... e.g +27831234567): ");
        String cell = sc.nextLine();

        String regResult = login.registerUser(username, password, cell);
        System.out.println(regResult);

        if (!regResult.equals("User registered successfully.")) {
            sc.close();
            return;
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String enteredUser = sc.nextLine();

        System.out.print("Enter password: ");
        String enteredPass = sc.nextLine();

        boolean loggedIn = login.loginUser(enteredUser, enteredPass, username, password);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (loggedIn) {
            System.out.println("Welcome to QuickChat.");
            boolean keepRunning = true;
            int totalMessagesSent = 0;

            while (keepRunning) {
                System.out.println("\nMENU OPTIONS:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Select choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("How many messages do you wish to enter? ");
                        int numMessages = Integer.parseInt(sc.nextLine());
                        Message[] batchArray = new Message[numMessages];

                        for (int i = 0; i < batchArray.length; i++) {
                            System.out.println("\n--- Entry (" + (i + 1) + " of " + numMessages + ") ---");
                            System.out.print("Enter Recipient Cell Number: ");
                            String recCell = sc.nextLine();

                            System.out.print("Enter Message (max 250 chars): ");
                            String text = sc.nextLine(); // FIXED: now takes full sentence

                            Message temp = new Message(recCell, text, totalMessagesSent + 1);

                            if (!temp.checkRecipientCell()) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                i--;
                                continue;
                            }

                            String status = temp.validateMessageLength();
                            System.out.println("Status: " + status);

                            if (status.equals("Message sent")) {
                                batchArray[i] = temp;
                                totalMessagesSent++;
                                System.out.println("\n>>> MESSAGE DETAILS <<<");
                                System.out.println(temp.printMessageDetails());
                            } else {
                                System.out.println("Please try again with shorter message.");
                                i--;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Coming Soon.");
                        break;
                    case 3:
                        System.out.println("Total messages sent: " + totalMessagesSent + ". Quitting...");
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Choose 1-3.");
                }
            }
        }
        sc.close();
    }
}
