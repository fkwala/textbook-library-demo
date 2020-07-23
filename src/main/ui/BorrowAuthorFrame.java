package ui;

import exception.EmptyStringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BorrowAuthorFrame extends GeneralBorrowTemplate {
    // submit using "ENTER" key: https://www.rgagnon.com/javadetails/java-0253.html
    JLabel authorInstructions;
    JTextField authorAns;

    // JFrame that requires user to fill in Author
    public BorrowAuthorFrame() {
        super();
        authorInstructions = new JLabel("Title (Press ENTER to submit): ");
        authorAns = new JTextField(10);
        authorAns.addKeyListener(this);
        add(authorInstructions);
        add(authorAns);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: if "ENTER" key is pressed and author exists in current list, redirect user to Edition JFrame; If "ENTER"
    //          key is pressed and author does not exist in current list, displays message indicating that book is
    //          unavailable; Otherwise, do nothing
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String inputAuthor = authorAns.getText();
            try {
                LibraryData.getInstance().listAuthor(inputAuthor);
                if (0 == LibraryData.getInstance().lengthTempbList()) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "Sorry, there is no such book in the library!");
                } else {
                    setVisible(false);
                    EditionFrame editionFrame = new EditionFrame();
                    editionFrame.setVisible(true);
                }
            } catch (EmptyStringException emptyString) {
                JOptionPane.showMessageDialog(new JFrame(), "Author not entered");
            }
        }
    }

}
