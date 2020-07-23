package persistence;

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
public class WriterTest {
    private static final String TEST_FILE = "./data/testTextbooks.txt";
    private static final String ERROR_FILE = "./data/Wrongfile.jpg";
    private Writer testWriter;
    private TextBook tb1;
    private TextBook tb2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        tb1 = new TextBook("CPSC110", "Gregor", 2);
        tb2 = new TextBook("CPSC121", "Patrice", 4);
    }

    @Test
    void testWriteTextbooks() {
        tb2.borrowed();
        testWriter.write(tb1);
        testWriter.write(tb2);
        testWriter.close();

        try {
            List<TextBook> textBookList = Reader.readTextBooks(new File(TEST_FILE));
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
        tb2.borrowed();
        testWriter.write(tb1);
        testWriter.write(tb2);
        testWriter.close();

        try {
            List<TextBook> textBookList = Reader.readTextBooks(new File(ERROR_FILE));
            fail("IOException should have been thrown");
        } catch (IOException e) {
            System.out.println("IOException caught: Wrong file type");
        }
    }
}
