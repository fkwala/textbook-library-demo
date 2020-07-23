package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for TextBook class
class TextBookTest {
    private TextBook tb;

    @BeforeEach
    void runBefore() {
        tb = new TextBook("Sample Title", "Felicia Kwala",0);
    }

    @Test
    void testConstructor() {
        assertEquals("Sample Title", tb.getTitle());
        assertEquals("Felicia Kwala", tb.getAuthor());
        assertEquals(0,tb.getEdition());
        assertTrue(tb.isAvail());
    }

    @Test
    void testBorrowed() {
        tb.borrowed();
        assertFalse(tb.isAvail());
    }

    @Test
    void testsetAvail() {
        tb.setAvail(false);
        assertFalse(tb.isAvail());

        tb.setAvail(true);
        assertTrue(tb.isAvail());
    }

}