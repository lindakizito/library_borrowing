package dao;

import model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private Connection conn;

    public MemberDAO(Connection conn) {
        this.conn = conn;
    }

    // Add a new member to the database
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (member_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";

        // Use try-with-resources to automatically close the statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getMemberId());
            stmt.setString(2, member.getFirstName());
            stmt.setString(3, member.getLastName());
            stmt.setString(4, member.getEmail());
            stmt.executeUpdate();
        }
    }

    // Retrieve all members from the database
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                        rs.getString("member_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );
                members.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }
}
