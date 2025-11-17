import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.MemberDAO;
import model.Book;
import model.BookChart;
import model.DatabaseConnection;
import model.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            return;
        }

        BookDAO bookDAO = new BookDAO(connection);
        BorrowRecordDAO borrowRecordDAO = new BorrowRecordDAO(connection);
        MemberDAO memberDAO = new MemberDAO(connection);

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Add Borrow Record");
            System.out.println("4. Show Chart");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Enter member id: "); String mid = scanner.nextLine();
                    System.out.print("First name: "); String fn = scanner.nextLine();
                    System.out.print("Last name: "); String ln = scanner.nextLine();
                    System.out.print("Email: "); String em = scanner.nextLine();
                    memberDAO.addMember(new Member(mid, fn, ln, em));
                    System.out.println("Member added.");

                } else if (choice == 2) {
                    System.out.print("Enter book id: "); String bid = scanner.nextLine();
                    System.out.print("Title: "); String ti = scanner.nextLine();
                    System.out.print("Author: "); String au = scanner.nextLine();
                    System.out.print("Genre: "); String ge = scanner.nextLine();
                    bookDAO.addBook(new Book(bid, ti, au, ge));
                    System.out.println("Book added.");

                } else if (choice == 3) {
                    System.out.print("Enter record id: "); String rid = scanner.nextLine();
                    System.out.print("Member id: "); String mid = scanner.nextLine();
                    System.out.print("Book id: "); String bid = scanner.nextLine();
                    System.out.print("Borrow date (YYYY-MM-DD): "); String bdate = scanner.nextLine();
                    System.out.print("Return date (YYYY-MM-DD): "); String rdate = scanner.nextLine();
                    System.out.print("Days borrowed: "); int days = scanner.nextInt(); scanner.nextLine();
                    borrowRecordDAO.addBorrowRecord(rid, mid, bid, bdate, rdate, days);
                    System.out.println("Borrow record added.");

                } else if (choice == 4) {
                    System.out.println("Charts Menu:");
                    System.out.println("1. Top Borrowed Books (Bar Chart)");
                    System.out.println("2. Genre Distribution (Pie Chart)");
                    System.out.println("3. Borrow Duration Histogram");
                    System.out.print("Select chart: ");
                    int chartChoice = scanner.nextInt(); scanner.nextLine();

                    if (chartChoice == 1) {
                        BookChart.displayBarChart(bookDAO.getTopBorrowedBooks(10));
                    } else if (chartChoice == 2) {
                        BookChart.displayPieChart(bookDAO.getGenreDistribution());
                    } else if (chartChoice == 3) {
                        BookChart.displayHistogram(borrowRecordDAO.getAllDaysBorrowed());
                    } else {
                        System.out.println("Unknown chart option.");
                    }
                } else if (choice == 0) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}
