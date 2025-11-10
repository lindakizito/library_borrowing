package dao;

import model.BorrowRecord;
import java.sql.*;

public class BorrowRecordDAO { // Handles BORROW RECORD operations

    private Connection connection;

    // Receives database connection
    public BorrowRecordDAO(Connection connection) {
        this.connection = connection;
    }

    // Adds a borrow record to DB
    public void addBorrowRecord(BorrowRecord record) throws SQLException {
        String sql = "INSERT INTO borrow_records (record_id, member_id, book_id, borrow_date, return_date, days_borrowed) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, record.getRecordId());
        stmt.setString(2, record.getMemberId());
        stmt.setString(3, record.getBookId());
        stmt.setDate(4, java.sql.Date.valueOf(record.getBorrowDate()));

        // Return date may be null → still allowed
        if (record.getReturnDate() == null) {
            stmt.setNull(5, Types.DATE);
        } else {
            stmt.setDate(5, java.sql.Date.valueOf(record.getReturnDate()));
        }

        stmt.setInt(6, record.getDaysBorrowed());

        stmt.executeUpdate();
        stmt.close();
    }
}
