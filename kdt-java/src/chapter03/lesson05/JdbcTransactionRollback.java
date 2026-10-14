package chapter03.lesson05;

import java.sql.*;

public class JdbcTransactionRollback {
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=REQUIRED";
    private static final String USER = "root";
    private static final String PASS = LocalDbConfig.password();


    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);

            try {
                String sql = "INSERT INTO tray (item) VALUES (?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, "Java-first");
                    ps.executeUpdate();
                    ps.setString(1, "java-second");;
                    ps.executeUpdate();
                }
                conn.rollback();
                System.out.println("rolled back");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM tray WHERE item LIKE ?")) {
                ps.setString(1, "java-x");
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();

                    System.out.println("count=" + rs.getInt(1));
            }
            }
        }
    }
}
