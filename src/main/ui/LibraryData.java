package ui;

import model.Booklist;
import model.TextBook;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryData extends Booklist {
    private static LibraryData mainLibraryData = null;
    private static final String TEXTBOOKS_FILE = "./data/textbooks.txt";

    // EFFECTS: constructs a LibraryData with initial data
    private LibraryData() {
        loadLibrary();
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    protected void init() {
        ArrayList<String> titleList = new ArrayList<>(Arrays.asList("BIOL200","COMM105","COMM292","CPSC110",
                "CPSC110","GEOG103","GEOG103","MATH202","MATH202","MATH202"));
        ArrayList<String> authorList = new ArrayList<>(Arrays.asList("Anne","Elicia","Elicia","Greg","Ian","Grace",
                "Trev","Rae","Sam","Sam"));
        ArrayList<Integer> editionList = new ArrayList<>(Arrays.asList(7,7,7,3,3,5,4,10,1,2));
        for (int i = 0; i < titleList.size(); i++) {
            addTextBook(new TextBook(titleList.get(i), authorList.get(i),editionList.get(i)));
        }
    }

    // MODIFIES: this
    // EFFECTS: loads textbooks from TEXTBOOKS_FILE, if that file exists;
    // otherwise initializes library with default values
    protected void loadLibrary() {
        try {
            List<TextBook> textBooks = Reader.readTextBooks(new File(TEXTBOOKS_FILE));
            setActualbList(textBooks);
        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of textbooks to TEXTBOOKS_FILE
    protected void saveLibrary() {
        try {
            Writer writer = new Writer(new File(TEXTBOOKS_FILE));
            for (TextBook tb : getActualbList()) {
                writer.write(tb);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(),"Unable to save textbooks to " + TEXTBOOKS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(new JFrame(),"Library List successfully updated!");
    }


    // EFFECTS: gets instance of the mainLibraryData
    public static LibraryData getInstance() {
        if (mainLibraryData == null) {
            mainLibraryData = new LibraryData();
        }
        return mainLibraryData;
    }
}
