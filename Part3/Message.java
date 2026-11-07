import java.io.*;
import java.util.ArrayList;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag;

    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();
    public static ArrayList<String> allRecipients = new ArrayList<>();
    public static ArrayList<String> allMessages = new ArrayList<>();

    public Message(String recipient, String messageText, String flag) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
    }

    public Message() {}

    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }

    public static boolean checkRecipientCell(String cell) {
        return cell != null && cell.length() == 12 && cell.startsWith("+27");
    }

    public static String createMessageID() {
        long num = (long)(Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(num);
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase().replaceAll("[^A-Z]", "");
        String idPart = messageID.substring(0, 2);
        int count = messageIDs.size() + 1;
        return idPart + ":" + count + ":" + first + last;
    }

    public static String sentMessage(String choice) {
        if (choice.equals("1")) return "Message successfully sent.";
        if (choice.equals("2")) return "Press 0 to delete message.";
        if (choice.equals("3")) return "Message successfully stored.";
        return "Message disregarded.";
    }

    public static void addMessage(Message m) {
        messageIDs.add(m.getMessageID());
        messageHashes.add(m.getMessageHash());
        allRecipients.add(m.getRecipient());
        allMessages.add(m.getMessageText());
        if (m.getFlag().equalsIgnoreCase("Sent")) {
            sentMessages.add(m.getMessageText());
        } else if (m.getFlag().equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(m.getMessageText());
        } else if (m.getFlag().equalsIgnoreCase("Stored")) {
            storedMessages.add(m.getMessageText());
            storedRecipients.add(m.getRecipient());
        }
    }

    public static void populateTestData() {
        sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear(); storedRecipients.clear();
        allRecipients.clear(); allMessages.clear();
        addMessage(new Message("+27834557896", "Did you get the cake?", "Sent"));
        addMessage(new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        addMessage(new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
        addMessage(new Message("08388884567", "It is dinner time !", "Sent"));
        addMessage(new Message("+27838884567", "Ok, I am leaving without you.", "Stored"));
    }

    public static String displaySenderRecipient() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedMessages.size(); i++) {
            sb.append("Sender: User, Recipient: ").append(storedRecipients.get(i))
              .append(", Message: ").append(storedMessages.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static String displayLongestMessage() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        String longest = storedMessages.get(0);
        for (String s : storedMessages) {
            if (s.length() > longest.length()) longest = s;
        }
        return longest;
    }

    public static String searchByID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Recipient: " + allRecipients.get(i) + ", Message: " + allMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    public static String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allRecipients.size(); i++) {
            if (allRecipients.get(i).equals(recipient)) {
                sb.append(allMessages.get(i)).append("\n");
            }
        }
        if (sb.length() == 0) return "No messages found for this recipient.";
        return sb.toString();
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String msg = allMessages.get(i);
                String rec = allRecipients.get(i);
                messageHashes.remove(i); messageIDs.remove(i);
                allRecipients.remove(i); allMessages.remove(i);
                storedMessages.remove(msg); sentMessages.remove(msg); disregardedMessages.remove(msg);
                storedRecipients.remove(rec);
                return "Message: " + msg + " successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public static String displayReport() {
        if (messageHashes.isEmpty()) return "No messages.";
        StringBuilder sb = new StringBuilder();
        sb.append("=== MESSAGE REPORT ===\n");
        for (int i = 0; i < messageHashes.size(); i++) {
            sb.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
            sb.append("Recipient: ").append(allRecipients.get(i)).append("\n");
            sb.append("Message: ").append(allMessages.get(i)).append("\n");
            sb.append("----------------------\n");
        }
        return sb.toString();
    }

    public static void writeToJSON() {
        try (PrintWriter out = new PrintWriter(new FileWriter("storedMessages.json"))) {
            out.println("[");
            for (int i = 0; i < storedMessages.size(); i++) {
                out.println("  {\"recipient\":\"" + storedRecipients.get(i) + "\", \"message\":\"" + storedMessages.get(i).replace("\"","") + "\"}" + (i < storedMessages.size()-1 ? "," : ""));
            }
            out.println("]");
        } catch (IOException e) { System.out.println("Error writing JSON: " + e.getMessage()); }
    }

    public static String readJSON() {
        storedMessages.clear(); storedRecipients.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("storedMessages.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("\"recipient\"")) {
                    String rec = line.split("\"recipient\":\"")[1].split("\"")[0];
                    String msg = line.split("\"message\":\"")[1].split("\"")[0];
                    storedRecipients.add(rec);
                    storedMessages.add(msg);
                }
            }
        } catch (IOException e) { return "No JSON file found. Store a message first."; }
        return displaySenderRecipient();
    }

    public String printMessage() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }
}
