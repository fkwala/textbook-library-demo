package model;

import exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Booklist class
public class BooklistManagerTest {
    BooklistManager blManager;
    TextBook tb1;
    TextBook tb2;
    TextBook tb3;
    TextBook tb4;
    TextBook tb5;
    TextBook tb6;
    TextBook tb7;
    String title1;
    String title2;
    String title3;
    String title4;
    String author1;
    String author2;
    String author3;
    String author4;
    LinkedList<TextBook> mainList;
    LinkedList<TextBook> tbList;


    @BeforeEach
    void runBefore() {
        mainList = new LinkedList<>();
        blManager = new BooklistManager();
        tb1 = new TextBook("Algebra", "Anne", 1);
        tb2 = new TextBook("Biology", "Boston", 2);
        tb3 = new TextBook("Computer Science", "Charlie", 3);
        tb4 = new TextBook("Algebra", "Boston", 4);
        tb5 = new TextBook("Algebra", "Charlie", 5);
        tb6 = new TextBook("Computer Science", "Charlie", 7);
        tb7 = new TextBook("Algebra", "Audrey", 1);
        title1 = "Algebra";
        title2 = "Computer Science";
        title3 = "ALGEBRA";
        title4 = "algebra";
        author1 = "Charlie";
        author2 = "Anne";
        author3 = "CHARLIE";
        author4 = "charlie";
        tbList = new LinkedList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(tbList, blManager.tempbList);
        assertEquals(0, blManager.tempbList.size());
    }


    @Test
    void testCapsListTitleNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);

        tbList.add(tb1);
        tbList.add(tb4);
        tbList.add(tb5);
        try {
            blManager.innerListTitle(title3,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, blManager.tempbList);
        assertEquals(3, blManager.tempbList.size());
    }

    @Test
    void testNoCapsListTitleNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);

        tbList.add(tb1);
        tbList.add(tb4);
        tbList.add(tb5);
        try {
            blManager.innerListTitle(title4,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, blManager.tempbList);
        assertEquals(3, blManager.tempbList.size());
    }

    @Test
    void testCapsListAuthorNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);

        tbList.add(tb3);
        tbList.add(tb5);
        tbList.add(tb6);
        try {
            blManager.innerListAuthor(author3,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, blManager.tempbList);
        assertEquals(3, blManager.tempbList.size());
    }

    @Test
    void testNoCapsListAuthorNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);

        tbList.add(tb3);
        tbList.add(tb5);
        tbList.add(tb6);
        try {
            blManager.innerListAuthor(author4,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, blManager.tempbList);
        assertEquals(3, blManager.tempbList.size());
    }

    @Test
    void testEditionSingleBookNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);
        try {
            blManager.innerListTitle(title3,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertTrue(blManager.innerHasEdition(1));
        assertFalse(blManager.innerHasEdition(2));

        assertEquals(title1, blManager.tempBook.getTitle());
        assertEquals(author2, blManager.tempBook.getAuthor());
        assertEquals(1, blManager.tempBook.getEdition());
    }

    @Test
    void testEditionMultipleBookNotEmpty() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);
        mainList.add(tb4);
        mainList.add(tb5);
        mainList.add(tb6);
        mainList.add(tb7);

        tbList.add(tb1);
        tbList.add(tb7);
        try {
            blManager.innerListTitle(title1,mainList);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertFalse(blManager.innerHasEdition(1));
        assertEquals(tbList, blManager.tempbList);
        assertEquals(2, blManager.tempbList.size());
    }

    @Test
    void testTitleEmptyStringException() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);

        try {
            blManager.innerListTitle("",mainList);
            fail("EmptyStringException should be thrown");
        } catch (EmptyStringException emptyString) {
            //expected
        }
    }

    @Test
    void testAuthorEmptyStringException() {
        mainList.add(tb1);
        mainList.add(tb2);
        mainList.add(tb3);

        try {
            blManager.innerListAuthor("",mainList);
            fail("EmptyStringException should be thrown");
        } catch (EmptyStringException emptyString) {
            //expected
        }
    }

}
