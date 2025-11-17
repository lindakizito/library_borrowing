package dao;

import model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDAO {
    private Connection connection;

    public MemberDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (member_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, member.getMemberId());
        stmt.setString(2, member.getFirstName());
        stmt.setString(3, member.getLastName());
        stmt.setString(4, member.getEmail());
        stmt.executeUpdate();
        stmt.close();
    }
}
