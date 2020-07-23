package ui;

import exception.EmptyStringException;
import model.TextBook;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// TextBook Library Application
public class Library {
    private static final String TEXTBOOKS_FILE = "./data/textbooks.txt";
    private Scanner input;
    static ArrayList<String> titleList = new ArrayList<>(Arrays.asList("BIOL200","COMM105","COMM292","CPSC110",
            "CPSC110","GEOG103","GEOG103","MATH202","MATH202","MATH202"));
    static ArrayList<String> authorList = new ArrayList<>(Arrays.asList("Anne","Elicia","Elicia","Greg","Ian","Grace",
            "Trev","Rae","Sam","Sam"));
    static ArrayList<Integer> editionList = new ArrayList<>(Arrays.asList(7,7,7,3,3,5,4,10,1,2));

    // EFFECTS: runs the library application
    public Library() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    protected void runLibrary() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        loadLibrary();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                System.out.println("Have a nice day!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    protected void processCommand(String command) {
        if (command.equals("D")) {
            doDonate();
        } else if (command.equals("B")) {
            doBorrow();
        } else if (command.equals("V")) {
            doViewList();
        } else if (command.equals("S")) {
            saveTextBook();
        } else {
            System.out.println("Selection not valid.");
        }
    }

    // EFFECTS: displays menu of options to user
    protected void displayMenu() {
        System.out.println("Welcome! Please choose the following: ");
        System.out.println("D -> Donate");
        System.out.println("B -> Borrow");
        System.out.println("V -> View booklist");
        System.out.println("S -> Save booklist to file");
        System.out.println("Q -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes library
    protected void init() {
        for (int i = 0; i < titleList.size(); i++) {
            LibraryData.getInstance().addTextBook(new TextBook(titleList.get(i), authorList.get(i),editionList.get(i)));
        }
    }

    // MODIFIES: this
    // EFFECTS: loads textbooks from TEXTBOOKS_FILE, if that file exists;
    // otherwise initializes library with default values
    protected void loadLibrary() {
        try {
            List<TextBook> textBooks = Reader.readTextBooks(new File(TEXTBOOKS_FILE));
            LibraryData.getInstance().setActualbList(textBooks);
        } catch (IOException e) {
            init();
        }
    }

    // EFFECTS: saves state of textbooks to TEXTBOOKS_FILE
    protected void saveTextBook() {
        try {
            Writer writer = new Writer(new File(TEXTBOOKS_FILE));
            for (TextBook tb : LibraryData.getInstance().getActualbList()) {
                writer.write(tb);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save textbooks to " + TEXTBOOKS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts the donation of textbook to library
    protected void doDonate() {
        System.out.println("Please fill in the details." + "\nWhat is the textbook title?");
        String dbTitle = input.next();
        dbTitle = dbTitle.toUpperCase();

        System.out.println("Who is the author?");
        String dbAuthor = input.next();
        dbAuthor = dbAuthor.substring(0,1).toUpperCase() + dbAuthor.substring(1).toLowerCase();

        System.out.println("What is the textbook edition?");
        int dbEdition = input.nextInt();

        TextBook donateBook = new TextBook(dbTitle,dbAuthor,dbEdition);

        if (LibraryData.getInstance().addTextBook(donateBook)) {
            System.out.println("Textbook successfully donated. Thank you!");
        } else {
            System.out.println("Sorry the textbook already exists in the library. Textbook is not added into library.");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts the loan of textbook from library
    protected void doBorrow() {
        String searchOption = "";
        while (! (searchOption.equals("T") || searchOption.equals("A"))) {
            searchOption = searchMethod();
        }
        selectionChoice(searchOption);
        if (0 == LibraryData.getInstance().lengthTempbList()) {
            System.out.println("Sorry, there is no such book in the library! \nRedirecting you to the main menu");
        } else {
            searchEdition();
        }
    }

    private void selectionChoice(String searchOption) {
        if (searchOption.equals("T")) {
            System.out.println("What title are you trying to find?");
            String inputTitle = input.next();
            try {
                LibraryData.getInstance().listTitle(inputTitle);
            } catch (EmptyStringException emptyString) {
                System.out.println("Title cannot be empty!");
            }
        } else {
            System.out.println("Which author are you trying to find?");
            String inputAuthor = input.next();
            try {
                LibraryData.getInstance().listAuthor(inputAuthor);
            } catch (EmptyStringException emptyString) {
                System.out.println("Author cannot be empty!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts search for textbook in libraryList for specific edition
    private void searchEdition() {
        System.out.println("Which edition are you looking for?");
        int inputEdition = input.nextInt();
        if (LibraryData.getInstance().hasEdition(inputEdition)) {
            if (LibraryData.getInstance().bookAvail()) {
                finalBorrow();
            } else {
                System.out.println("The book is currently on loan. Please come back again!");
                LibraryData.getInstance().inittempbList();
            }
        } else if (LibraryData.getInstance().lengthTempbList() == 0) {
            System.out.println("Sorry, there is no such book in the library! \nRedirecting you to the main menu");
        } else {
            narrowbList();
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts final borrowing of the book
    private void finalBorrow() {
        System.out.println("The book we have found is " + LibraryData.getInstance().gettempTitle() + "(Edition "
                + LibraryData.getInstance().gettempEdition() + ")     Written by "
                + LibraryData.getInstance().gettempAuthor());
        System.out.println("Please confirm your loan. \nY -> Yes \nN -> No");
        String inputBorrow = input.next();
        inputBorrow = inputBorrow.toUpperCase();
        if (inputBorrow.equals("Y")) {
            System.out.println("Congratulations! You have successfully borrowed the book. \nRedirecting you "
                    + "to the main menu");
            LibraryData.getInstance().bookBorrowed();
            LibraryData.getInstance().inittempbList();
        } else {
            System.out.println("Bringing you back to the main menu.");
        }
    }

    // MODIFIES: this
    // EFFECTS: if prints narrowed tempbList and redirects user to main menu
    private void narrowbList() {
        System.out.println("Here are the list of books we have narrowed down: ");
        for (TextBook tb : LibraryData.getInstance().getTempbList()) {
            String tbStatus;
            if (tb.isAvail()) {
                tbStatus = "Available in library";
            } else {
                tbStatus = "Currently on loan";
            }
            System.out.println(tb.getTitle() + "  (Edition " + tb.getEdition() + ")"
                    + "   Written by " + tb.getAuthor()
                    + "        Status: " + tbStatus);
        }
        LibraryData.getInstance().inittempbList();
        System.out.println("Redirecting you to the main menu");
    }

    // EFFECTS: conducts search for textbook according to specified method
    protected String searchMethod() {
        String searchOption;
        System.out.println("How would you like to find your book?"
                + "\nT -> Title"
                + "\nA -> Author");
        searchOption = input.next();
        searchOption = searchOption.toUpperCase();
        return searchOption;
    }

    // EFFECTS: prints list of textbooks and availability status
    private void doViewList() {
        for (TextBook tb : LibraryData.getInstance().getActualbList()) {
            String tbStatus;
            if (tb.isAvail()) {
                tbStatus = "Available in library";
            } else {
                tbStatus = "Currently on loan";
            }
            System.out.println(tb.getTitle() + "  (Edition " + tb.getEdition() + ")"
                    + "   Written by " + tb.getAuthor()
                    + "        Status: " + tbStatus);
        }
    }
}
