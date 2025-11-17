package dao;

import model.Book;
import java.sql.*;
import java.util.*;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (book_id, title, author, genre) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, book.getBookId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setString(4, book.getGenre());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Book> getTopBorrowedBooks(int limit) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, b.author, b.genre, COUNT(br.record_id) AS borrow_count " +
                "FROM books b JOIN borrow_records br ON b.book_id = br.book_id " +
                "GROUP BY b.book_id ORDER BY borrow_count DESC LIMIT ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, limit);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Book book = new Book(rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"));
            book.setBorrowCount(rs.getInt("borrow_count"));
            books.add(book);
        }
        rs.close();
        stmt.close();
        return books;
    }

    public Map<String, Integer> getGenreDistribution() throws SQLException {
        Map<String, Integer> genreMap = new HashMap<>();
        String sql = "SELECT genre, COUNT(*) AS gcount FROM books GROUP BY genre";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            genreMap.put(rs.getString("genre"), rs.getInt("gcount"));
        }
        rs.close();
        stmt.close();
        return genreMap;
    }
}
