package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GeneralBorrowTemplate extends JFrame implements KeyListener {

    public GeneralBorrowTemplate() {
        super("Book Loan Page");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 150));
        getContentPane().setBackground(Color.getHSBColor(100,30,200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // EFFECTS: if "ENTER" key is pressed and title exists in current list, redirect user to Edition JFrame; If "ENTER"
    //          key is pressed and title does not exist in current list, displays message indicating that book is
    //          unavailable; Otherwise, do nothing
    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public void keyReleased(KeyEvent e) {}
}
