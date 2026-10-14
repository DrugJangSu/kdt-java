package chapter03.lesson05;

import java.sql.*;

public class JdbcPing {
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=REQUIRED";
    private static final String USER = "root";
    private static final String PASS = LocalDbConfig.password();

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery();
        ) {
            rs.next();

            System.out.println("db ping " + rs.getInt(1));

        }
    }

}
