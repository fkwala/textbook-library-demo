package persistence;

import model.Booklist;
import model.TextBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testParseBookListFile1() {
        JsonReader reader = new JsonReader("./data/testBooklistFile1.json");
        try {
            Booklist bookList = reader.read();
            List<TextBook> textBookList = bookList.getActualbList();
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
    void testParseBookListFile2() {
        JsonReader reader = new JsonReader("./data/testBooklistFile2.json");
        try {
            Booklist bookList = reader.read();
            List<TextBook> textBookList = bookList.getActualbList();
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
    void testNonExistentFile() {
        JsonReader reader = new JsonReader("./path/does/not/exist/testBookList.json");
        try {
            Booklist bookList = reader.read();
            fail("IOException should have been thrown");
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}
