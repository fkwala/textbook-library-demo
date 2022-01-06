package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Reader;
import persistence.Savable;
import persistence.Writable;

import java.io.PrintWriter;

// A TextBook containing the book title, author, edition and availability in the list
public class TextBook implements Writable {
    private String title;
    private String author;
    private int edition;
    private boolean avail;

    // REQUIRES: ti and au both have a non-zero length
    // EFFECTS: textbook has given title, author and edition, and is available
    public TextBook(String ti, String au, int ed) {
        this.title = ti;
        this.author = au;
        this.edition = ed;
        this.avail = true;
    }

    // REQUIRES: ti and au both have a non-zero length
    // EFFECTS: textbook has given title, author, edition and availability; to be used in data persistence
    public TextBook(String ti, String au, int ed, boolean av) {
        this.title = ti;
        this.author = au;
        this.edition = ed;
        this.avail = av;
    }

    // EFFECTS: returns title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns author
    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns edition
    public int getEdition() {
        return edition;
    }

    // EFFECTS: returns availability
    public Boolean isAvail() {
        return avail;
    }

    // MODIFIES: this
    // EFFECTS: changes availability of textbook to borrowed
    public void borrowed() {
        this.avail = false;
    }

    // MODIFIES: this
    // EFFECTS: changes availability of textbook
    public void setAvail(boolean newBool) {
        avail = newBool;
    }

    // @Override
    // public void save(PrintWriter printWriter) {
    //     printWriter.print(title);
    //     printWriter.print(Reader.DELIMITER);
    //     printWriter.print(author);
    //     printWriter.print(Reader.DELIMITER);
    //     printWriter.print(edition);
    //     printWriter.print(Reader.DELIMITER);
    //     printWriter.println(avail);
    // }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("edition", edition);
        json.put("avail", avail);
        return json;
    }
}
