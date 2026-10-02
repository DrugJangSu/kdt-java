package chapter03.lesson05;

import java.sql.*;

public class JdbcPostWorkFlow {
    private static final String URL = "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";
    private static final String PASS = "kdtpass";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1) 첫번째 회원의 id를 조회함. 회원이 없으면 no jdbc_member를 출력하고 종료한다.
            int memberId= 0;
            String memberSql = "SELECT id FROM jdbc_member ORDER BY id DESC LIMIT 1";

            try (PreparedStatement ps = conn.prepareStatement(memberSql);
            ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    System.out.println("no jdbc_member");
                    return;

                }
                memberId = rs.getInt("id");
            }
        // 2) 제목 jdbc-flow가 이미 있는지, 없으면 postId는 0으로 남긴다.
        int postId = 0;
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM jdbc_post WHERE ptitle = ? ORDER BY id;");) {

            ps.setString(1, "jdbc-flow");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    postId = rs.getInt("id");
                }
            }
        }
        // 3) 행이 없을 때만 본문이 draft인 게시글을 한번 추가함.


        }
    }
}
