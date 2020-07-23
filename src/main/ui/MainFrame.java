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


public class MainFrame extends JFrame implements ActionListener {
    private static final String TEXTBOOKS_FILE = "./data/textbooks.txt";

    // TextBook Library GUI Application
    public MainFrame() {
        super("Main Menu");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());
        JLabel heading = new JLabel("~Textbook Library~");
        heading.setFont(new Font("Courier",Font.BOLD,25));
        heading.setBorder(new EmptyBorder(20,45,20,45));
        add(heading,BorderLayout.NORTH); // Organise components: https://coderanch.com/t/344096/java/flow-lay-force-add-component
        add(setImage(),BorderLayout.CENTER);
        add(setButtons(),BorderLayout.SOUTH);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: returns a JPanel with an image in it
    private JPanel setImage() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        BufferedImage mainImg = null;
        try {
            mainImg = ImageIO.read(new File("./data/libraryIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image mainScaledImg = mainImg.getScaledInstance(230, 230, Image.SCALE_SMOOTH);  // Resizing picture to fit JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
        JLabel bookImage = new JLabel(new ImageIcon(mainScaledImg));   // Adding picture: https://stackoverflow.com/questions/18027833/adding-image-to-jframe
        imagePanel.add(bookImage);
        return imagePanel;
    }

    // EFFECTS: returns a JPanel with 4 buttons ("Donate", "Borrow", "View", "Save") in it
    private JPanel setButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton btnDonate = new JButton("Donate");
        btnDonate.setActionCommand("bookDonate");
        btnDonate.addActionListener(this);
        JButton btnBorrow = new JButton("Borrow");
        btnBorrow.setActionCommand("bookBorrow");
        btnBorrow.addActionListener(this);
        JButton btnView = new JButton("View");
        btnView.setActionCommand("bookView");
        btnView.addActionListener(this);
        JButton btnSave = new JButton("Save");
        btnSave.setActionCommand("bookSave");
        btnSave.addActionListener(this);
        buttonPanel.add(btnDonate);
        buttonPanel.add(btnBorrow);
        buttonPanel.add(btnView);
        buttonPanel.add(btnSave);
        return buttonPanel;
    }

    // EFFECTS: redirects user to another JFrame corresponding to the button clicked by user
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "bookDonate":
                DonateFrame donFrame = new DonateFrame();
                donFrame.setVisible(true);
                break;
            case "bookBorrow":
                BorrowFrame borFrame = new BorrowFrame();
                borFrame.setVisible(true);
                break;
            case "bookView":
                ViewFrame viewFrame = new ViewFrame();
                viewFrame.setVisible(true);
                break;
            case "bookSave":
                LibraryData.getInstance().saveLibrary();
                break;
            default:
                //empty
        }
    }

}
