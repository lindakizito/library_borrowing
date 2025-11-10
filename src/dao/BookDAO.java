package dao;

import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO { /* Handles all BOOK database operations */

    private Connection connection;

    //DAO receives an existing DB connection
    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserts a book into the database
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (book_id, title, author, genre) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, book.getBookId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setString(4, book.getGenre());

        stmt.executeUpdate(); // run SQL
        stmt.close();
    }

    // Retrieves all books from DB and returns a List of Book objects
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            books.add(new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre")
            ));
        }

        rs.close();
        stmt.close();
        return books;
    }
}
