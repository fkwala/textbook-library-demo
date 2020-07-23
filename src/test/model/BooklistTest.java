package model;

import exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Booklist class
public class BooklistTest {
    Booklist bl1;
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
    LinkedList<TextBook> tbList;


    @BeforeEach
    void runBefore() {
        bl1 = new Booklist();
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
        assertEquals(tbList, bl1.getActualbList());
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(0, bl1.lengthActualbList());
        assertEquals(0, bl1.lengthTempbList());
    }

    @Test
    void testAddSingleBook() {
        assertTrue(bl1.addTextBook(tb1));
        assertFalse(bl1.addTextBook(tb1));
        tbList.add(tb1);
        assertEquals(tbList, bl1.getActualbList());
        assertEquals(1, bl1.lengthActualbList());
    }

    @Test
    void testAddMultipleBooks() {
        assertTrue(bl1.addTextBook(tb1));
        assertTrue(bl1.addTextBook(tb2));
        assertTrue(bl1.addTextBook(tb3));
        assertFalse(bl1.addTextBook(tb1));
        assertFalse(bl1.addTextBook(tb2));
        assertFalse(bl1.addTextBook(tb3));
        tbList.add(tb1);
        tbList.add(tb2);
        tbList.add(tb3);
        assertEquals(tbList, bl1.getActualbList());
        assertEquals(3, bl1.lengthActualbList());
    }

    @Test
    void testCapsListTitleNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        tbList.add(tb1);
        tbList.add(tb4);
        tbList.add(tb5);
        try {
            bl1.listTitle(title3);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(3, bl1.lengthTempbList());
    }

    @Test
    void testNoCapsListTitleNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        tbList.add(tb1);
        tbList.add(tb4);
        tbList.add(tb5);
        try {
            bl1.listTitle(title4);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(3, bl1.lengthTempbList());
    }

    @Test
    void testCapsListAuthorNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        tbList.add(tb3);
        tbList.add(tb5);
        tbList.add(tb6);
        try {
            bl1.listAuthor(author3);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(3, bl1.lengthTempbList());
    }

    @Test
    void testNoCapsListAuthorNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        tbList.add(tb3);
        tbList.add(tb5);
        tbList.add(tb6);
        try {
            bl1.listAuthor(author4);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(3, bl1.lengthTempbList());
    }

    @Test
    void testEditionSingleBookNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        try {
            bl1.listTitle(title3);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertTrue(bl1.hasEdition(1));
        assertFalse(bl1.hasEdition(2));

        assertEquals(title1, bl1.gettempTitle());
        assertEquals(author2, bl1.gettempAuthor());
        assertEquals(1, bl1.gettempEdition());
    }

    @Test
    void testEditionMultipleBookNotEmpty() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);
        bl1.addTextBook(tb7);

        tbList.add(tb1);
        tbList.add(tb7);
        try {
            bl1.listTitle(title1);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertFalse(bl1.hasEdition(1));
        assertEquals(tbList, bl1.getTempbList());
        assertEquals(2, bl1.lengthTempbList());
    }

    @Test
    void testTitleEmptyStringException() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);

        try {
            bl1.listTitle("");
            fail("EmptyStringException should be thrown");
        } catch (EmptyStringException emptyString) {
            //expected
        }
    }

    @Test
    void testAuthorEmptyStringException() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);

        try {
            bl1.listAuthor("");
            fail("EmptyStringException should be thrown");
        } catch (EmptyStringException emptyString) {
            //expected
        }
    }

    @Test
    void testBookAvail() {
        assertTrue(bl1.addTextBook(tb1));
        try {
            bl1.listTitle(title1);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertTrue(bl1.hasEdition(1));
        assertTrue(bl1.bookAvail());
    }

    @Test
    void testBookBorrowed() {
        assertTrue(bl1.addTextBook(tb1));
        try {
            bl1.listTitle(title1);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertTrue(bl1.hasEdition(1));
        bl1.bookBorrowed();
        assertFalse(bl1.bookAvail());
    }

    @Test
    void testsetactualbList() {
        assertEquals(tbList, bl1.getActualbList());
        assertEquals(0, bl1.lengthActualbList());

        tbList.add(tb1);
        tbList.add(tb2);
        tbList.add(tb3);
        bl1.setActualbList(tbList);

        assertEquals(tbList, bl1.getActualbList());
        assertEquals(3, bl1.lengthActualbList());
    }

    @Test
    void testinittempbList() {
        bl1.addTextBook(tb1);
        bl1.addTextBook(tb2);
        bl1.addTextBook(tb3);
        bl1.addTextBook(tb4);
        bl1.addTextBook(tb5);
        bl1.addTextBook(tb6);

        try {
            bl1.listTitle(title1);
        } catch (EmptyStringException emptyString) {
            fail("EmptyStringException should not have been thrown");
        }
        assertEquals(3, bl1.lengthTempbList());
        bl1.inittempbList();
        assertEquals(0, bl1.lengthTempbList());
    }
}
