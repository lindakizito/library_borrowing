package dao;

import java.sql.*;
import java.util.*;

public class BorrowRecordDAO {
    private Connection connection;

    public BorrowRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBorrowRecord(String rid, String mid, String bid, String bdate, String rdate, int days) throws SQLException {
        String sql = "INSERT INTO borrow_records (record_id, member_id, book_id, borrow_date, return_date, days_borrowed) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, rid);
        stmt.setString(2, mid);
        stmt.setString(3, bid);
        stmt.setString(4, bdate);
        stmt.setString(5, rdate);
        stmt.setInt(6, days);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Integer> getAllDaysBorrowed() throws SQLException {
        List<Integer> durations = new ArrayList<>();
        String sql = "SELECT days_borrowed FROM borrow_records";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            durations.add(rs.getInt("days_borrowed"));
        }
        rs.close();
        stmt.close();
        return durations;
    }
}
