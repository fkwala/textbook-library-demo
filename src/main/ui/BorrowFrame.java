package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BorrowFrame extends JFrame implements ActionListener {
    JLabel spaceBufferImage;
    JLabel spaceBufferButton;

    // JFrame that displays option to search library through "Title" or "Author"
    public BorrowFrame() {
        super("Book Loan Page");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 250));
        getContentPane().setBackground(Color.getHSBColor(100,30,200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());
        JLabel instructions = new JLabel("Please choose your search method: ");
        add(instructions,BorderLayout.NORTH);
        add(setBorrowImages(),BorderLayout.CENTER);
        add(setBorrowButtons(),BorderLayout.SOUTH);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: returns a JPanel with 2 images for Title and Author in it
    private JPanel setBorrowImages() {
        JPanel borrowImagePanel = new JPanel();
        borrowImagePanel.setLayout(new FlowLayout());
        borrowImagePanel.setBackground(Color.getHSBColor(100,30,200));
        BufferedImage borrowTitleImage = null;
        BufferedImage borrowAuthorImage = null;
        try {
            borrowTitleImage = ImageIO.read(new File("./data/borrowTitleIcon.png"));
            borrowAuthorImage = ImageIO.read(new File("./data/borrowAuthorIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image titleScaledImg = borrowTitleImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);  // Resizing picture to fit JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
        JLabel bookTitleImage = new JLabel(new ImageIcon(titleScaledImg));   // Adding picture: https://stackoverflow.com/questions/18027833/adding-image-to-jframe
        Image authorScaledImg = borrowAuthorImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH);  // Resizing picture to fit JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
        JLabel bookAuthorImage = new JLabel(new ImageIcon(authorScaledImg));   // Adding picture: https://stackoverflow.com/questions/18027833/adding-image-to-jframe
        spaceBufferImage = new JLabel("     ");
        borrowImagePanel.add(bookTitleImage);
        borrowImagePanel.add(spaceBufferImage);
        borrowImagePanel.add(bookAuthorImage);
        return borrowImagePanel;
    }

    // EFFECTS: returns a JPanel with 2 buttons ("Title", "Author") in it
    private JPanel setBorrowButtons() {
        JPanel borrowButtonPanel = new JPanel();
        borrowButtonPanel.setLayout(new FlowLayout());
        borrowButtonPanel.setBackground(Color.getHSBColor(100,30,200));
        JButton btnTitle = new JButton("Title");
        JButton btnAuthor = new JButton("Author");
        btnTitle.setActionCommand("searchTitle");
        btnTitle.addActionListener(this);
        btnAuthor.setActionCommand("searchAuthor");
        btnAuthor.addActionListener(this);
        spaceBufferButton = new JLabel("               ");
        borrowButtonPanel.add(btnTitle);
        borrowButtonPanel.add(spaceBufferButton);
        borrowButtonPanel.add(btnAuthor);
        return borrowButtonPanel;
    }

    // EFFECTS: redirects user to another JFrame corresponding to the button clicked by user
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        switch (e.getActionCommand()) {
            case "searchTitle":
                BorrowTitleFrame titleFrame = new BorrowTitleFrame();
                titleFrame.setVisible(true);
                break;
            case "searchAuthor":
                BorrowAuthorFrame authorFrame = new BorrowAuthorFrame();
                authorFrame.setVisible(true);
                break;
            default:
                //empty
        }
    }

}
