package model;

import exception.EmptyStringException;

import java.util.LinkedList;
import java.util.List;

// BooklistManager that manages the internal operation of borrowing in Booklist
public class BooklistManager {
    protected List<TextBook> tempbList;
    protected TextBook tempBook;

    // EFFECTS: constructs a new BooklistManager
    public BooklistManager() {
        tempbList =  new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: if title has zero length, throw EmptyStringException;
    //          otherwise, produce filtered tempbList containing list of textbooks with specified title
    public void innerListTitle(String title, List<TextBook> mainList) throws EmptyStringException {
        if (title.equals("")) {
            throw new EmptyStringException();
        }
        title = title.toLowerCase();
        for (TextBook tb : mainList) {
            if (tb.getTitle().toLowerCase().equals(title)) {
                tempbList.add(tb);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if author has zero length, throw EmptyStringException;
    //          otherwise, produce filtered tempbList containing list of textbooks with specified author
    public void innerListAuthor(String author, List<TextBook> mainList) throws EmptyStringException {
        if (author.equals("")) {
            throw new EmptyStringException();
        }
        author = author.toLowerCase();
        for (TextBook tb : mainList) {
            if (tb.getAuthor().toLowerCase().equals(author)) {
                tempbList.add(tb);
            }
        }
    }

    // REQUIRES: tempbList is not empty
    // MODIFIES: this
    // EFFECTS: returns true if there is only 1 such edition is in the tempbList
    //          otherwise, returns false
    public Boolean innerHasEdition(int edition) {
        LinkedList<TextBook> editionList = new LinkedList();
        for (TextBook tb : tempbList) {
            if (edition == tb.getEdition()) {
                editionList.add(tb);
            }
        }
        if (editionList.size() == 1) {
            tempBook = editionList.getFirst();
            return true;
        } else {
            tempbList = editionList;
            return false;
        }
    }
}
