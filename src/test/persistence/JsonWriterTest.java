package persistence;

import model.Booklist;
import model.TextBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Writer class
public class JsonWriterTest {
    private static final String TEST_FILE = "./data/testBookList.json";
    Booklist bl;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        bl = new Booklist();
        bl.addTextBook(new TextBook("CPSC110", "Gregor", 2));
        bl.addTextBook(new TextBook("CPSC121", "Patrice", 4, false));
    }

    @Test
    void testWriteTextbooks() {
        try {
            JsonWriter testWriter = new JsonWriter(TEST_FILE);
            testWriter.open();
            testWriter.write(bl);
            testWriter.close();

            JsonReader reader = new JsonReader(TEST_FILE);
            Booklist bookList = reader.read();
            List<TextBook> textBookList = bookList.getActualbList();
            TextBook testtb1 = textBookList.get(0);
            assertEquals("CPSC110", testtb1.getTitle());
            assertEquals("Gregor", testtb1.getAuthor());
            assertEquals(2, testtb1.getEdition());
            assertTrue(testtb1.isAvail());

            TextBook testtb2 = textBookList.get(1);
            assertEquals("CPSC121", testtb2.getTitle());
            assertEquals("Patrice", testtb2.getAuthor());
            assertEquals(4, testtb2.getEdition());
            assertFalse(testtb2.isAvail());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWrongFileType() {
        try {
            JsonWriter errorWriter = new JsonWriter("./data/WrongFile.json");
            errorWriter.open();
            fail("IOException should have been thrown");
        } catch (IOException e) {
            System.out.println("IOException caught: Wrong file type");
        }
    }
}
