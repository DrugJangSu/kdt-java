package chapter03.lesson05;

import java.sql.*;

public class JdbcPostInsert {
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            int memberId = 0;
            String findSql = "SELECT id FROM jdbc_member ORDER BY id limit 1;";
            try (PreparedStatement find = conn.prepareStatement(findSql);
            ResultSet rs = find.executeQuery()) {

            if (!rs.next()) {
                System.out.println("no jdbc_member");
                return;
            }
            memberId = rs.getInt("id");
            }
            String sql = "INSERT INTO jdbc_post (member_id, title, body) VALUES (?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, memberId);
                ps.setString(2, "jdbc");
                ps.setString(3, "from java");
                int n = ps.executeUpdate();

                System.out.println("inserted  " + n);
            }
        }
    }
}
