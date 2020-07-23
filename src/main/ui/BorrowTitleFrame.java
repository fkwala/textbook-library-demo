package ui;

import exception.EmptyStringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class BorrowTitleFrame extends GeneralBorrowTemplate {
    // submit using "ENTER" key: https://www.rgagnon.com/javadetails/java-0253.html
    JLabel titleInstructions;
    JTextField titleAns;

    // JFrame that requires user to fill in Title
    public BorrowTitleFrame() {
        super();
        titleInstructions = new JLabel("Title (Press ENTER to submit): ");
        titleAns = new JTextField(10);
        titleAns.addKeyListener(this);
        add(titleInstructions);
        add(titleAns);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: if "ENTER" key is pressed and title exists in current list, redirect user to Edition JFrame; If "ENTER"
    //          key is pressed and title does not exist in current list, displays message indicating that book is
    //          unavailable; Otherwise, do nothing
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String inputTitle = titleAns.getText();
            try {
                LibraryData.getInstance().listTitle(inputTitle);
                if (0 == LibraryData.getInstance().lengthTempbList()) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "Sorry, there is no such book in the library!");
                } else {
                    setVisible(false);
                    EditionFrame editionFrame = new EditionFrame();
                    editionFrame.setVisible(true);
                }
            } catch (EmptyStringException emptyString) {
                JOptionPane.showMessageDialog(new JFrame(), "Title not entered");
            }
        }
    }
}
