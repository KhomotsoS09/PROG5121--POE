import java.util.Scanner;

public class LoginApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter first name: ");
        String first = sc.nextLine();
        System.out.print("Enter last name: ");
        String last = sc.nextLine();
        System.out.print("Enter username (must contain _ and max 5 chars): ");
        String user = sc.nextLine();
        System.out.print("Enter password (8+ chars, capital, number, special): ");
        String pass = sc.nextLine();
        System.out.print("Enter cellphone (+27... 12 chars): ");
        String cell = sc.nextLine();

        System.out.println(login.registerUser(user, pass, cell));

        System.out.println("=== LOGIN ===");
        System.out.print("Enter username: ");
        String lu = sc.nextLine();
        System.out.print("Enter password: ");
        String lp = sc.nextLine();
        boolean ok = login.loginUser(lu, lp);
        System.out.println(login.returnLoginStatus(ok, first, last));
        if (!ok) { sc.close(); return; }

        System.out.println("Welcome to QuickChat.");

        int choice = 0;
        while (choice != 3) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose: ");
            try { choice = Integer.parseInt(sc.nextLine()); } catch (Exception e) { choice = 0; }

            if (choice == 1) {
                System.out.print("How many messages do you want to send? ");
                int num = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < num; i++) {
                    System.out.print("Enter recipient (+27...): ");
                    String rec = sc.nextLine();
                    if (!Message.checkRecipientCell(rec)) {
                        System.out.println("Cell phone number incorrectly formatted. Must be +27 and 12 chars.");
                        i--; continue;
                    }
                    System.out.print("Enter message (max 250 chars): ");
                    String txt = sc.nextLine();
                    if (txt.length() > 250) { System.out.println("Message too long."); i--; continue; }
                    System.out.println("1. Send  2. Disregard  3. Store");
                    System.out.print("Choose: ");
                    String opt = sc.nextLine();
                    String flag = opt.equals("1") ? "Sent" : opt.equals("3") ? "Stored" : "Disregard";
                    Message m = new Message(rec, txt, flag);
                    Message.addMessage(m);
                    System.out.println(Message.sentMessage(opt));
                    System.out.println(m.printMessage());
                    if (flag.equals("Stored")) Message.writeToJSON();
                }
            } else if (choice == 2) {
                System.out.println("Recently Sent Messages:");
                for (String s : Message.sentMessages) System.out.println(s);
            } else if (choice == 4) {
                int sub = 0;
                while (sub != 7) {
                    System.out.println("\n--- STORED MESSAGES MENU ---");
                    System.out.println("1. Display sender and recipient of all stored messages");
                    System.out.println("2. Display longest stored message");
                    System.out.println("3. Search for message ID");
                    System.out.println("4. Search for messages for a recipient");
                    System.out.println("5. Delete message using message hash");
                    System.out.println("6. Display report");
                    System.out.println("7. Back to Main Menu");
                    System.out.print("Choose: ");
                    try { sub = Integer.parseInt(sc.nextLine()); } catch (Exception e) { sub = 0; }
                    if (sub == 1) {
                        System.out.println(Message.readJSON());
                        System.out.println(Message.displaySenderRecipient());
                    } else if (sub == 2) {
                        System.out.println("Longest: " + Message.displayLongestMessage());
                    } else if (sub == 3) {
                        System.out.print("Enter Message ID: ");
                        String id = sc.nextLine();
                        System.out.println(Message.searchByID(id));
                    } else if (sub == 4) {
                        System.out.print("Enter recipient: ");
                        String r = sc.nextLine();
                        System.out.println(Message.searchByRecipient(r));
                    } else if (sub == 5) {
                        System.out.println(Message.displayReport());
                        System.out.print("Enter Message Hash to delete: ");
                        String h = sc.nextLine();
                        System.out.println(Message.deleteByHash(h));
                    } else if (sub == 6) {
                        System.out.println(Message.displayReport());
                    }
                }
            }
        }
        System.out.println("Total messages sent: " + Message.sentMessages.size());
        sc.close();
    }
}
