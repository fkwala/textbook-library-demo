package ui;

import model.TextBook;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DonateFrame extends JFrame implements ActionListener {
    private JTextField fieldTitle;
    private JTextField fieldAuthor;
    private JSpinner editionSpinner;

    // JFrame that requires user to fill in information of textbook to be donated
    public DonateFrame() {
        super("Donation Page");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 400));
        getContentPane().setBackground(Color.getHSBColor(100, 153, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        setDonateAppearance();
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: adds components to JFrame
    private void setDonateAppearance() {
        JButton btn = new JButton("Confirm Donation");
        btn.addActionListener(this);
        BufferedImage donateImg = null;
        try {
            donateImg = ImageIO.read(new File("./data/donateBookIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image donateScaledImg = donateImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH);  // Resizing picture to fit JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
        JLabel donateBookImage = new JLabel(new ImageIcon(donateScaledImg));   // Adding picture: https://stackoverflow.com/questions/18027833/adding-image-to-jframe
        fieldTitle = new JTextField(10);
        fieldAuthor = new JTextField(10);
        SpinnerNumberModel editionModel = new SpinnerNumberModel(0, 0, 100, 1);
        editionSpinner = new JSpinner(editionModel);
        add(donateBookImage);
        add(new JLabel("Textbook Title: "));
        add(fieldTitle);
        add(new JLabel("Textbook Author: "));
        add(fieldAuthor);
        add(new JLabel("Textbook Edition: "));
        add(editionSpinner);
        add(btn);
    }

    // EFFECTS: compile information to form a textbook
    //          If textbook is not already in the current list, add textbook to current list and display message about
    //          successful donation; Otherwise, display message about unsuccessful donation
    public void actionPerformed(ActionEvent e) {
        String curTitle = fieldTitle.getText();
        curTitle = curTitle.toUpperCase();
        String curAuthor = fieldAuthor.getText();
        curAuthor = curAuthor.substring(0, 1).toUpperCase() + curAuthor.substring(1).toLowerCase();

        int curEdition = (Integer) editionSpinner.getValue();
        TextBook donateBook = new TextBook(curTitle, curAuthor, curEdition);
        if (LibraryData.getInstance().addTextBook(donateBook)) {
            JOptionPane.showMessageDialog(new JFrame(), "Textbook successfully donated. Thank you!");
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Sorry the textbook already exists in the library. "
                    + "Textbook is not added into library.");
        }
        setVisible(false);
    }
}
