import dao.BookDAO;
import dao.MemberDAO;
import dao.BorrowRecordDAO;

import model.Book;
import model.Member;
import model.BorrowRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // Connect to database
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            return;
        }

        // Create DAO objects for performing DB operations
        BookDAO bookDAO = new BookDAO(connection);
        MemberDAO memberDAO = new MemberDAO(connection);
        BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO(connection);

        // Create a sample member
        Member member = new Member(
                "M051",
                "Jane",
                "Doe",
                "Jane@example.com"
        );

        // Create a sample book
        Book book = new Book(
                "B031",
                "Harry Potter",
                "J.K. Rowling",
                "Fantasy"
        );

        try {
            // Insert sample member into database
            memberDAO.addMember(member);

            // Insert sample book into database
            bookDAO.addBook(book);

            System.out.println("Sample member and book stored in database.");

            // Create a borrow record for the member borrowing the book
            BorrowRecord record = new BorrowRecord(
                    "R0101",
                    member.getMemberId(),
                    book.getBookId(),
                    LocalDate.now(),
                    LocalDate.parse("2025-12-15"),
                    0     // days borrowed
            );

            // Insert borrow record into database
            borrowRecordDAO.addBorrowRecord(record);

            System.out.println("Borrow record created.");

            // Display all books in database
            System.out.println("\nBooks in database:");
            for (Book b : bookDAO.getAllBooks()) {
                System.out.println("- " + b.getTitle());
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        System.out.println("Program finished.");
    }
}
