import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryUI extends JFrame {

    JTextField bookId, title, author, publisher, year, copies;
    JTextArea displayArea;

    public LibraryUI() {
        setTitle("Library Management System");
        setSize(600, 500);
        setLayout(new FlowLayout());

        // Input Fields
        bookId = new JTextField(10);
        title = new JTextField(10);
        author = new JTextField(10);
        publisher = new JTextField(10);
        year = new JTextField(5);
        copies = new JTextField(5);

        add(new JLabel("Book ID")); add(bookId);
        add(new JLabel("Title")); add(title);
        add(new JLabel("Author")); add(author);
        add(new JLabel("Publisher")); add(publisher);
        add(new JLabel("Year")); add(year);
        add(new JLabel("Copies")); add(copies);

        // Buttons
        JButton addBtn = new JButton("Add Book");
        JButton viewBtn = new JButton("View Books");

        add(addBtn);
        add(viewBtn);

        displayArea = new JTextArea(15, 50);
        add(new JScrollPane(displayArea));

        // Add Book Action
        addBtn.addActionListener(e -> addBook());

        // View Books Action
        viewBtn.addActionListener(e -> viewBooks());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Add Book
    void addBook() {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO BOOKS VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(bookId.getText()));
            ps.setString(2, title.getText());
            ps.setString(3, author.getText());
            ps.setString(4, publisher.getText());
            ps.setInt(5, Integer.parseInt(year.getText()));
            ps.setInt(6, Integer.parseInt(copies.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Added!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // View Books
    void viewBooks() {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM BOOKS";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            displayArea.setText("");

            while (rs.next()) {
                displayArea.append(
                        rs.getInt("Book_ID") + " | " +
                                rs.getString("Title") + " | " +
                                rs.getString("Author") + " | " +
                                rs.getInt("Copies") + "\n"
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LibraryUI();
    }
}
