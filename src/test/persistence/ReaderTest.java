package persistence;

import model.TextBook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Reader class
public class ReaderTest {

    @Test
    void testConstructor() {
        Reader testReader = new Reader();
    }

    @Test
    void testParseAccountsFile1() {
        try {
            List<TextBook> textBookList = Reader.readTextBooks(new File("./data/testTextbooksFile1.txt"));
            TextBook testtb1 = textBookList.get(0);
            assertEquals("MATH105", testtb1.getTitle());
            assertEquals("Alice", testtb1.getAuthor());
            assertEquals(5,testtb1.getEdition());
            assertTrue(testtb1.isAvail());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<TextBook> textBookList = Reader.readTextBooks(new File("./data/testTextbooksFile2.txt"));
            TextBook testtb1 =  textBookList.get(0);
            assertEquals("COMM101", testtb1.getTitle());
            assertEquals("Connor", testtb1.getAuthor());
            assertEquals(3,testtb1.getEdition());
            assertTrue(testtb1.isAvail());

            TextBook testtb2 =  textBookList.get(1);
            assertEquals("ECON102", testtb2.getTitle());
            assertEquals("Gateman", testtb2.getAuthor());
            assertEquals(5,testtb2.getEdition());
            assertFalse(testtb2.isAvail());

            TextBook testtb3 =  textBookList.get(2);
            assertEquals("CHEM304", testtb3.getTitle());
            assertEquals("Sally", testtb3.getAuthor());
            assertEquals(8,testtb3.getEdition());
            assertTrue(testtb3.isAvail());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readTextBooks(new File("./path/does/not/exist/testAccount.txt"));
            fail("IOException should have been thrown");
        } catch (IOException e) {
            // expected
        }
    }
}
