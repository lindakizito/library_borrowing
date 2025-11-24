package model;

import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.MemberDAO;
import model.Book;
import model.BookChart;
import model.DatabaseConnection;
import model.Member;

import javax.swing.*;
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
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        // Buttons
        JButton addMemberBtn = new JButton("Add Member Manually");
        JButton uploadMembersBtn = new JButton("Upload Members File");
        JButton addBookBtn = new JButton("Add Book");
        JButton addBorrowBtn = new JButton("Add Borrow Record");
        JButton viewChartsBtn = new JButton("View Charts");
        JButton exitBtn = new JButton("Exit");

        add(addMemberBtn);
        add(uploadMembersBtn);
        add(addBookBtn);
        add(addBorrowBtn);
        add(viewChartsBtn);
        add(exitBtn);

        // Button Actions
        addMemberBtn.addActionListener(e -> addMemberManually());
        uploadMembersBtn.addActionListener(e -> uploadMembersFromFile());
        addBookBtn.addActionListener(e -> addBookManually());
        addBorrowBtn.addActionListener(e -> addBorrowRecordManually());
        viewChartsBtn.addActionListener(e -> viewCharts());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    // Add member manually
    private void addMemberManually() {
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
                Member member = new Member(
                        memberIdField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        emailField.getText()
                );
                MemberDAO memberDAO = new MemberDAO(connection);
                memberDAO.addMember(member);
                JOptionPane.showMessageDialog(this, "Member added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding member: " + ex.getMessage());
            }
        }
    }

    // Upload members from CSV file
    private void uploadMembersFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                MemberDAO memberDAO = new MemberDAO(connection);
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        Member member = new Member(parts[0], parts[1], parts[2], parts[3]);
                        memberDAO.addMember(member);
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(this, count + " members added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
            }
        }
    }

    // Add book manually
    private void addBookManually() {
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
                Book book = new Book(bookIdField.getText(), titleField.getText(), authorField.getText(), genreField.getText());
                BookDAO bookDAO = new BookDAO(connection);
                bookDAO.addBook(book);
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage());
            }
        }
    }

    // Add borrow record manually
    private void addBorrowRecordManually() {
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
                BorrowRecordDAO borrowDAO = new BorrowRecordDAO(connection);
                borrowDAO.addBorrowRecord(
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
                    default:
                        JOptionPane.showMessageDialog(this, "Unknown option selected.");
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
