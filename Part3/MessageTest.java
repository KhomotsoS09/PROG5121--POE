import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        Message.populateTestData();
        assertEquals("Did you get the cake?", Message.sentMessages.get(0));
        assertEquals("It is dinner time !", Message.sentMessages.get(1));
        assertEquals(2, Message.sentMessages.size());
    }

    @Test
    public void testDisplayLongestMessage() {
        Message.populateTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        Message.populateTestData();
        String id = Message.messageIDs.get(3);
        String result = Message.searchByID(id);
        assertTrue(result.contains("08388884567"));
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchForMessagesForRecipient() {
        Message.populateTestData();
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Message.populateTestData();
        String hash = Message.messageHashes.get(1);
        String result = Message.deleteByHash(hash);
        assertEquals("Message: Where are you? You are late! I have asked you to be on time. successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        Message.populateTestData();
        String report = Message.displayReport();
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time !"));
    }
}
