package persistence;

import model.TextBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read textbook data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: constructs a reader that will read textbook data from file
    public Reader() { }

    // EFFECTS: returns a list of textbooks parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<TextBook> readTextBooks(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of textbooks parsed from list of strings
    // where each string contains data for one textbook
    private static List<TextBook> parseContent(List<String> fileContent) {
        List<TextBook> textbooks = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            textbooks.add(parseTextbook(lineComponents));
        }

        return textbooks;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // title, element 1 represents the author, element 2 represents
    // the edition and element 3 represents the availability of the
    // textbook to be constructed
    // EFFECTS: returns a textbook constructed from components
    private static TextBook parseTextbook(List<String> components) {
        String title = components.get(0);
        String author = components.get(1);
        int edition = Integer.parseInt(components.get(2));
        boolean avail = Boolean.parseBoolean(components.get(3));
        TextBook newtb = new TextBook(title,author,edition);
        newtb.setAvail(avail);
        return newtb;
    }
}
