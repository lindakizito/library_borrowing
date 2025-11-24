package model;

import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.MemberDAO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainInterface extends JFrame {

    private Connection connection;

    public MainInterface() {
        // Initialize DB connection
        try {
            connection = DatabaseConnection.getConnection();
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(0);
        }

        setTitle("Library Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Layout for buttons
        setLayout(new GridLayout(4, 1, 10, 10));

        // Main buttons
        JButton addManualBtn = new JButton("Add Manually");
        JButton uploadFileBtn = new JButton("Upload File");
        JButton viewChartsBtn = new JButton("View Charts");
        JButton exitBtn = new JButton("Exit");

        // Style all buttons
        styleButton(addManualBtn);
        styleButton(uploadFileBtn);
        styleButton(viewChartsBtn);
        styleButton(exitBtn);

        // Add buttons to frame
        add(addManualBtn);
        add(uploadFileBtn);
        add(viewChartsBtn);
        add(exitBtn);

        // Actions
        addManualBtn.addActionListener(e -> chooseManualAdd());
        uploadFileBtn.addActionListener(e -> chooseUploadFile());
        viewChartsBtn.addActionListener(e -> viewCharts());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    // Helper method to style main buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(144, 238, 144)); // Soft green
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2), // border around text
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // padding
        ));
    }

    // Helper method to style JOptionPane buttons
    private void styleOptionPaneButtons() {
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("Button.background", new Color(144, 238, 144)); // Soft green
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 16));
    }

    // Dialog to choose manual add type
    private void chooseManualAdd() {
        styleOptionPaneButtons();
        String[] options = {"Member", "Book", "Borrow Record"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select type to add manually:",
                "Add Manually",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice != null) {
            switch (choice) {
                case "Member": addMemberManually(); break;
                case "Book": addBookManually(); break;
                case "Borrow Record": addBorrowRecordManually(); break;
            }
        }
    }

    // Dialog to choose file upload type
    private void chooseUploadFile() {
        styleOptionPaneButtons();
        String[] options = {"Members", "Books", "Borrow Records"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select file type to upload:",
                "Upload File",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice != null) {
            switch (choice) {
                case "Members": uploadMembersFromFile(); break;
                case "Books": uploadBooksFromFile(); break;
                case "Borrow Records": uploadBorrowRecordsFromFile(); break;
            }
        }
    }

    // Upload members
    private void uploadMembersFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                MemberDAO dao = new MemberDAO(connection);
                String line; int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        dao.addMember(new Member(parts[0], parts[1], parts[2], parts[3]));
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(this, count + " members uploaded successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    // Upload books
    private void uploadBooksFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                BookDAO dao = new BookDAO(connection);
                String line; int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        dao.addBook(new Book(parts[0], parts[1], parts[2], parts[3]));
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(this, count + " books uploaded successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    // Upload borrow records
    private void uploadBorrowRecordsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                BorrowRecordDAO dao = new BorrowRecordDAO(connection);
                String line; int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        dao.addBorrowRecord(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]));
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(this, count + " borrow records uploaded successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    // Add member manually
    private void addMemberManually() {
        styleOptionPaneButtons();

        JTextField memberIdField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "Member ID:", memberIdField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                MemberDAO dao = new MemberDAO(connection);
                dao.addMember(new Member(memberIdField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText()));
                JOptionPane.showMessageDialog(this, "Member added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding member: " + ex.getMessage());
            }
        }
    }

    // Add book manually
    private void addBookManually() {
        styleOptionPaneButtons();

        JTextField bookIdField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();

        Object[] message = {
                "Book ID:", bookIdField,
                "Title:", titleField,
                "Author:", authorField,
                "Genre:", genreField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                BookDAO dao = new BookDAO(connection);
                dao.addBook(new Book(bookIdField.getText(), titleField.getText(), authorField.getText(), genreField.getText()));
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage());
            }
        }
    }

    // Add borrow record manually
    private void addBorrowRecordManually() {
        styleOptionPaneButtons();

        JTextField recordIdField = new JTextField();
        JTextField memberIdField = new JTextField();
        JTextField bookIdField = new JTextField();
        JTextField borrowDateField = new JTextField();
        JTextField returnDateField = new JTextField();
        JTextField daysBorrowedField = new JTextField();

        Object[] message = {
                "Record ID:", recordIdField,
                "Member ID:", memberIdField,
                "Book ID:", bookIdField,
                "Borrow Date (YYYY-MM-DD):", borrowDateField,
                "Return Date (YYYY-MM-DD):", returnDateField,
                "Days Borrowed:", daysBorrowedField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Borrow Record", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                BorrowRecordDAO dao = new BorrowRecordDAO(connection);
                dao.addBorrowRecord(
                        recordIdField.getText(),
                        memberIdField.getText(),
                        bookIdField.getText(),
                        borrowDateField.getText(),
                        returnDateField.getText(),
                        Integer.parseInt(daysBorrowedField.getText())
                );
                JOptionPane.showMessageDialog(this, "Borrow record added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding borrow record: " + ex.getMessage());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Days Borrowed must be a number!");
            }
        }
    }

    // View charts
    private void viewCharts() {
        styleOptionPaneButtons();
        String[] options = {"Top Borrowed Books (Bar Chart)", "Genre Distribution (Pie Chart)", "Borrow Duration Histogram"};
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select chart to display:",
                "View Charts",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice != null) {
            try {
                BookDAO bookDAO = new BookDAO(connection);
                BorrowRecordDAO borrowDAO = new BorrowRecordDAO(connection);

                switch (choice) {
                    case "Top Borrowed Books (Bar Chart)":
                        List<Book> topBooks = bookDAO.getTopBorrowedBooks(10);
                        BookChart.displayBarChart(topBooks);
                        break;
                    case "Genre Distribution (Pie Chart)":
                        Map<String, Integer> genreMap = bookDAO.getGenreDistribution();
                        BookChart.displayPieChart(genreMap);
                        break;
                    case "Borrow Duration Histogram":
                        List<Integer> durations = borrowDAO.getAllDaysBorrowed();
                        BookChart.displayHistogram(durations);
                        break;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainInterface gui = new MainInterface();
            gui.setVisible(true);
        });
    }
}
