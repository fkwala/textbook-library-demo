package persistence;

import model.Booklist;
import model.TextBook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code modified from JsonSerializationDemo

// Represents a reader that reads day info from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bookList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Booklist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bookList from JSON object and returns it
    private Booklist parseBookList(JSONObject jsonObject) {
        Booklist bookList = new Booklist();
        addTextBooks(bookList, jsonObject);
        return bookList;
    }

    // MODIFIES: bookList
    // EFFECTS: parses textbooks from JSON object and adds to bookList
    private void addTextBooks(Booklist bookList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("bookList");
        for (Object json : jsonArray) {
            JSONObject nextTextBook = (JSONObject) json;
            addTextBook(bookList, nextTextBook);
        }
    }

    /// MODIFIES: day
    // EFFECTS: parses 1 textbook from JSON object and adds to bookList
    private void addTextBook(Booklist bookList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int edition = jsonObject.getInt("edition");
        boolean avail = jsonObject.getBoolean("avail");
        TextBook textBook = new TextBook(title, author, edition, avail);
        bookList.addTextBook(textBook);
    }
}