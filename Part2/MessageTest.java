package com.mycompany.loginapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message("+27831234567", "Hi tonight", 0);
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        Message msg = new Message("0831234567", "Hi tonight", 0);
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testValidateMessageLength_Valid() {
        Message msg = new Message("+27831234567", "Hi tonight", 0);
        assertEquals("Message sent", msg.validateMessageLength());
    }

    @Test
    public void testValidateMessageLength_Exceeds() {
        String longText = "a".repeat(251);
        Message msg = new Message("+27831234567", longText, 0);
        assertTrue(msg.validateMessageLength().contains("exceeds"));
    }

    @Test
    public void testGenerateMessageHash() {
        Message msg = new Message("+27831234567", "Hi tonight", 0);
        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27831234567", "Test", 1);
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }
}
