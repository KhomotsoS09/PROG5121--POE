package com.mycompany.loginapp;
import java.util.Random;
public class Message {
    private String messageId;
    private String recipientCell;
    private String payloadText;
    private int currentMessageNumber;
    public Message(String recipientCell, String payloadText, int currentMessageNumber) {
        this.messageId = generateMessageID();
        this.recipientCell = recipientCell;
        this.payloadText = payloadText;
        this.currentMessageNumber = currentMessageNumber;
    }
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
    public boolean checkMessageID() {
        return this.messageId!= null && this.messageId.length() == 10;
    }
    public boolean checkRecipientCell() {
        return this.recipientCell!= null && this.recipientCell.matches("^\\+27\\d{9}$");
    }
    public String validateMessageLength() {
        if (this.payloadText.length() > 250) {
            return "Message exceeds 250 characters by " + (this.payloadText.length() - 250) + ", please reduce size.";
        }
        return "Message sent";
    }
    public String generateMessageHash() {
        String firstTwoId = this.messageId.substring(0, 2);
        String[] words = this.payloadText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        return firstTwoId + ":" + this.currentMessageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }
    public String printMessageDetails() {
        return "MessageID: " + this.messageId + "\nMessage Hash: " + generateMessageHash() + "\nRecipient: " + this.recipientCell + "\nMessage: " + this.payloadText;
    }
}
