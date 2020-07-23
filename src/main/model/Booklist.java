package model;

import exception.EmptyStringException;

import java.util.LinkedList;
import java.util.List;


// Booklist contains one actual Textbook list and one BooklistManager which are initialised with each new constructor
public class Booklist {
    private List<TextBook> actualbList;
    private BooklistManager mainManager;

    // EFFECTS: constructs an empty book collection
    public Booklist() {
        actualbList = new LinkedList<>();
        mainManager = new BooklistManager();
    }

    // MODIFIES: this
    // EFFECT: if textbook is in actualbList, return false
    //         otherwise, add textbook to actualbList and return true
    public Boolean addTextBook(TextBook newBook) {
        for (TextBook tb : actualbList) {
            if ((tb.getTitle().equals(newBook.getTitle())) && (tb.getAuthor().equals(newBook.getAuthor()))
                    && (tb.getEdition() == newBook.getEdition())) {
                return false;
            }
        }
        actualbList.add(newBook);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: if title has zero length, throw EmptyStringException;
    //          otherwise, produce filtered list in mainManager containing list of textbooks with specified title
    public void listTitle(String title) throws EmptyStringException {
        mainManager.innerListTitle(title,actualbList);
    }

    // MODIFIES: this
    // EFFECTS: if author has zero length, throw EmptyStringException;
    //          otherwise, produce filtered list in mainManager containing list of textbooks with specified author
    public void listAuthor(String author) throws EmptyStringException {
        mainManager.innerListAuthor(author,actualbList);
    }

    // REQUIRES: tempbList is not empty
    // MODIFIES: this
    // EFFECTS: returns true if there is only 1 such edition is in the existing list in mainManager
    //          otherwise, returns false
    public Boolean hasEdition(int edition) {
        return mainManager.innerHasEdition(edition);
    }

    // EFFECTS: returns tempBook title
    public String gettempTitle() {
        return mainManager.tempBook.getTitle();
    }

    // EFFECTS: returns tempBook title
    public String gettempAuthor() {
        return mainManager.tempBook.getAuthor();
    }

    // EFFECTS: returns tempBook edition
    public int gettempEdition() {
        return mainManager.tempBook.getEdition();
    }

    // EFFECTS: returns true if tempBook is available
    //          otherwise, return false
    public Boolean bookAvail() {
        return mainManager.tempBook.isAvail();
    }

    // REQUIRES: tempBook.isAvail is true
    // MODIFIES: this
    // EFFECT: change status of tempBook from available to unavailable
    public void bookBorrowed() {
        mainManager.tempBook.borrowed();
    }

    // MODIFIES: this
    // EFFECT: changes actualbList
    public void setActualbList(List<TextBook> tblist) {
        actualbList = tblist;
    }

    // EFFECTS: returns actualbList
    public List<TextBook> getActualbList() {
        return actualbList;
    }

    // EFFECTS: returns tempbList in mainManager
    public List<TextBook> getTempbList() {
        return mainManager.tempbList;
    }

    // EFFECT: returns length of actualbList in Booklist
    public int lengthActualbList() {
        return actualbList.size();
    }

    // EFFECT: returns length of tempbList in mainManager
    public int lengthTempbList() {
        return mainManager.tempbList.size();
    }

    // EFFECT: initialises tempbList in mainManager to empty
    public void inittempbList() {
        mainManager.tempbList = new LinkedList<>();
    }
}

