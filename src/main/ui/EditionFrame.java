package ui;

import model.TextBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EditionFrame extends GeneralBorrowTemplate {
    // submit using "ENTER" key: https://www.rgagnon.com/javadetails/java-0253.html
    JTextField editionAns;

    // JFrame that requires user to fill in edition integer
    public EditionFrame() {
        super();
        JLabel editionInstructions = new JLabel("Edition: ");
        editionAns = new JTextField(5);
        editionAns.addKeyListener(this);
        add(editionInstructions);
        add(editionAns);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // REQUIRES: editionAns is int
    // EFFECTS: conducts search for textbook in libraryList for specific edition.
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            int inputEdition = Integer.parseInt(editionAns.getText());
            if (LibraryData.getInstance().hasEdition(inputEdition)) {
                if (LibraryData.getInstance().bookAvail()) {
                    finalBorrow();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(),
                            "The book is currently on loan. Please come back again!");
                    LibraryData.getInstance().inittempbList();
                    setVisible(false);
                }
            } else if (LibraryData.getInstance().lengthTempbList() == 0) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(new JFrame(),
                        "Sorry, there is no such book in the library!");
                setVisible(false);
            } else {
                narrowbList();
            }
        }
    }


    // EFFECTS: conducts final borrowing of the book
    private void finalBorrow() {
        String searchResult = "The book we have found is " + LibraryData.getInstance().gettempTitle() + "(Edition "
                + LibraryData.getInstance().gettempEdition() + ")     Written by "
                + LibraryData.getInstance().gettempAuthor() + "\n" + "Please confirm your loan.";
        if ((JOptionPane.showConfirmDialog(new JFrame(),
                searchResult,"Confirm Loan", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
            LibraryData.getInstance().bookBorrowed();
            LibraryData.getInstance().inittempbList();
            JOptionPane.showMessageDialog(new JFrame(),
                    "Congratulations! You have successfully borrowed the book. \nRedirecting you "
                            + "to the main menu");
        } else {
            JOptionPane.showMessageDialog(new JFrame(),"Bringing you back to the main menu.");

        }
        setVisible(false);
    }

    // EFFECTS: prints narrowed tempbList
    private void narrowbList() {
        String allTextbooks = "";
        for (TextBook tb : LibraryData.getInstance().getTempbList()) {
            String tbStatus;
            if (tb.isAvail()) {
                tbStatus = "Available in library";
            } else {
                tbStatus = "Currently on loan";
            }
            allTextbooks += (tb.getTitle() + "  (Edition " + tb.getEdition() + ")"
                    + "   Written by " + tb.getAuthor()
                    + "        Status: " + tbStatus + "\n");
        }
        JOptionPane.showMessageDialog(new JFrame(),"Here are the list of books we have narrowed down: \n"
                + allTextbooks + "Redirecting you to the main menu");
        LibraryData.getInstance().inittempbList();
        setVisible(false);
    }
}

