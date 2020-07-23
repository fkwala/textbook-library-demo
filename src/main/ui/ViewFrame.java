package ui;

import model.TextBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewFrame extends JFrame {

    // JFrame that displays the current list of Textbooks in Library
    public ViewFrame() {
        super("All Books in the Library");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        getContentPane().setBackground(Color.getHSBColor(100,250,250));
        setLayout(new FlowLayout());
        setListedView();
        pack();

        setLocationRelativeTo(null);
        setResizable(true);
    }

    // EFFECTS: compiles and adds individual textbook information to JFrame
    private void setListedView() {
        for (TextBook tb : LibraryData.getInstance().getActualbList()) {
            String tbStatus;
            if (tb.isAvail()) {
                tbStatus = "Available in library";
            } else {
                tbStatus = "Currently on loan";
            }
            JLabel indivTextBook = new JLabel(tb.getTitle() + "  (Edition " + tb.getEdition() + ")"
                    + "   Written by " + tb.getAuthor()
                    + "        Status: " + tbStatus);
            indivTextBook.setFont(new Font("Courier",Font.PLAIN,15));
            add(indivTextBook);
        }
    }
}
