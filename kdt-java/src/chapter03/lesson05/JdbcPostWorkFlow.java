package chapter03.lesson05;

import java.sql.*;

public class JdbcPostWorkFlow {

    // DB 주소. "내 컴퓨터(localhost)의 3306번 문에 있는 MySQL의 kdt라는 DB"
    private static final String URL =
            "jdbc:mysql://localhost:3306/kdt?sslMode=DISABLED";
    private static final String USER = "root";    // DB 아이디
    private static final String PASS = "kdtpass"; // DB 비밀번호

    // main: 프로그램을 실행하면 가장 먼저 시작되는 곳 (모양이 정해져 있으니 외워두면 됨)
    // throws SQLException: "DB 작업하다 에러가 나면 여기서 처리 안 하고 그냥 밖으로 던질게"
    public static void main(String[] args) throws SQLException {

        // try ( ) { } : 괄호 안에서 빌린 것을 } 를 나갈 때 자동으로 반납(close)해주는 문법
        // DriverManager.getConnection(...) : 주소/아이디/비번으로 DB에 접속
        // Connection conn : 접속된 "연결 통로". 이 conn을 통해 SQL을 보냄
        // 이 try는 맨 끝까지 감싸고 있음 → 프로그램 내내 DB 연결을 유지하다가 마지막에 끊음
        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASS)) {

            // =========================================================
            // 1) 첫 번째 회원의 id 조회
            //    회원이 없으면 no jdbc_member 출력 후 종료
            // =========================================================

            // int: 정수(숫자) 타입
            // memberId라는 이름의 상자를 만들고 일단 0을 넣어둠
            // (아래에서 진짜 회원 id를 찾으면 그 값으로 바꿔 넣을 예정)
            int memberId = 0;

            // DB에 보낼 SQL문을 문자열로 저장
            // "jdbc_member 테이블에서 id를 작은 순서로 정렬해서 맨 위 1개만 줘"
            // ★ DESC(큰 순서)를 빼야 "첫 번째" 회원이 나옴
            String memberSql =
                    "SELECT id FROM jdbc_member ORDER BY id LIMIT 1;";

            // 두 가지를 빌림 (세미콜론 ; 으로 구분)
            // ps : conn.prepareStatement(SQL) → SQL을 DB에 보낼 준비를 마친 객체
            // rs : ps.executeQuery() → SELECT를 실행하고 받은 "결과 표"
            try (PreparedStatement ps = conn.prepareStatement(memberSql);
                 ResultSet rs = ps.executeQuery()) {

                // rs.next() : 결과 표에서 다음 줄로 이동. 줄이 있으면 true, 없으면 false
                // ! : 반대로 뒤집기 (true ↔ false)
                // 즉 !rs.next() 는 "다음 줄이 없으면" = "회원이 한 명도 없으면"
                if (!rs.next()) {
                    // System.out.println(...) : 괄호 안의 내용을 콘솔에 출력하고 줄바꿈
                    System.out.println("no jdbc_member");
                    // return : main을 여기서 즉시 끝냄 (프로그램 종료)
                    // 빌린 rs, ps, conn은 try 덕분에 자동으로 반납됨
                    return;
                }

                // 여기까지 왔다면 회원이 있다는 뜻 (위에서 next()로 첫 줄로 이동해 있는 상태)
                // rs.getInt("id") : 지금 줄에서 id 칸의 값을 정수로 꺼냄
                // = : 오른쪽 값을 왼쪽 상자에 넣음 → memberId가 0에서 실제 회원 id로 바뀜
                memberId = rs.getInt("id");
            } // 여기서 rs, ps 반납 (conn은 아직 사용 중)


            // =========================================================
            // 2) title이 jdbc-flow인 게시글이 이미 있는지 조회
            //    없으면 postId는 0으로 유지
            // =========================================================

            // 찾은 게시글 id를 담을 상자. 0 = "아직 못 찾음"이라는 표시로 사용
            int postId = 0;

            // SQL을 준비해서 ps로 빌림
            // "..." + "..." : 문자열 두 개를 이어붙이기. 한 줄이 너무 길어서 나눠 쓴 것뿐
            //   → 실제로는 "SELECT id FROM jdbc_post WHERE title = ? ORDER BY id;" 한 문장
            // WHERE title = ? : title이 ? 인 행만 찾기. ? 는 아직 비워둔 빈칸
            try (PreparedStatement ps =
                         conn.prepareStatement(
                                 "SELECT id FROM jdbc_post " +
                                         "WHERE title = ? ORDER BY id;"
                         )) {

                // 1번째 ? 빈칸에 "jdbc-flow"라는 글자를 채움 (번호는 1부터 시작!)
                ps.setString(1, "jdbc-flow");

                // 빈칸을 다 채웠으니 이제 실행 → 결과 표를 rs로 빌림
                // (빈칸을 먼저 채워야 실행할 수 있어서 try를 안쪽에 하나 더 연 것)
                try (ResultSet rs = ps.executeQuery()) {

                    // 결과에 줄이 있으면 = jdbc-flow 글이 이미 있으면
                    if (rs.next()) {
                        // 그 글의 id를 꺼내서 postId에 저장
                        postId = rs.getInt("id");
                    }
                    // 줄이 없으면 if 안으로 안 들어가니까 postId는 그대로 0
                } // rs 반납
            } // ps 반납


            // =========================================================
            // 3) jdbc-flow 게시글이 없을 때만 INSERT
            // =========================================================

            // == : 같은지 비교 (= 는 "넣기", == 는 "같은지 확인". 헷갈리기 쉬움!)
            // postId가 여전히 0이면 = 2단계에서 글을 못 찾았으면
            if (postId == 0) {

                // ---------- 3-1) 게시글 새로 추가 ----------

                // INSERT문 준비. (member_id, title, body) 칸에 (?, ?, ?) 값을 넣겠다
                try (PreparedStatement ps =
                             conn.prepareStatement(
                                     "INSERT INTO jdbc_post " +
                                             "(member_id, title, body) " +
                                             "VALUES (?, ?, ?);"
                             )) {

                    // 1번째 ? (member_id) ← 1단계에서 찾은 회원 id. 숫자라서 setInt
                    ps.setInt(1, memberId);

                    // 2번째 ? (title) ← "jdbc-flow". 글자라서 setString
                    ps.setString(2, "jdbc-flow");

                    // 3번째 ? (body) ← "draft"
                    ps.setString(3, "draft");

                    // executeUpdate() : INSERT/UPDATE/DELETE를 실행하는 메서드
                    //   → 결과 표 대신 "몇 줄이 바뀌었는지" 숫자를 돌려줌 (1줄 추가했으니 1)
                    // 실행과 출력을 한 줄에 같이 해서 "inserted 1"이 출력됨
                    System.out.println(
                            "inserted " + ps.executeUpdate()
                    );
                } // ps 반납


                // ---------- 3-2) 방금 추가한 글의 id 다시 찾기 ----------
                // 새로 넣은 글의 id는 DB가 자동으로 정해주기 때문에 Java는 아직 모름
                // 그래서 2단계와 똑같은 SELECT를 한 번 더 해서 id를 알아냄

                try (PreparedStatement ps =
                             conn.prepareStatement(
                                     "SELECT id FROM jdbc_post " +
                                             "WHERE title = ? ORDER BY id;"
                             )) {

                    ps.setString(1, "jdbc-flow"); // ? 빈칸 채우기

                    try (ResultSet rs = ps.executeQuery()) { // 실행 → 결과 표

                        if (rs.next()) {                 // 줄이 있으면 (방금 넣었으니 있음)
                            postId = rs.getInt("id");    // 그 id를 postId에 저장 → 이제 0이 아님
                        }
                    }
                }

            } else {
                // else : 위 if 조건이 틀렸을 때 실행 = postId가 0이 아님 = 글이 이미 있음
                // 이미 있으니 INSERT 안 하고 메시지만 출력
                // (이 경우 postId에는 2단계에서 찾은 id가 이미 들어 있음)
                System.out.println("inserted 0");
            }

            // 여기까지 오면 if든 else든 상관없이 postId에 jdbc-flow 글의 id가 들어 있음


            // =========================================================
            // 4) 해당 id 게시글의 body를 verified로 수정
            // =========================================================

            // UPDATE문: "id가 ?인 글의 body를 ?로 바꿔라"
            try (PreparedStatement ps =
                         conn.prepareStatement(
                                 "UPDATE jdbc_post " +
                                         "SET body = ? WHERE id = ?;"
                         )) {

                // 1번째 ? (SET body = ?) ← "verified"
                ps.setString(1, "verified");

                // 2번째 ? (WHERE id = ?) ← 수정할 글의 id
                ps.setInt(2, postId);

                // UPDATE 실행. 바뀐 줄 수를 돌려주지만 여기선 안 쓰니까 받지 않음
                ps.executeUpdate();
            } // ps 반납


            // =========================================================
            // 5) 최종 id, title, body 다시 조회해서 출력
            //    (진짜로 바뀌었는지 DB에서 다시 읽어와 확인하는 단계)
            // =========================================================

            // "id가 ?인 글의 id, title, body를 줘"
            try (PreparedStatement ps =
                         conn.prepareStatement(
                                 "SELECT id, title, body " +
                                         "FROM jdbc_post WHERE id = ?;"
                         )) {

                ps.setInt(1, postId); // ? 빈칸에 글 id 채우기

                try (ResultSet rs = ps.executeQuery()) { // 실행 → 결과 표

                    if (rs.next()) { // 첫 줄로 이동 (줄이 있으면)

                        // 지금 줄에서 칸별로 값을 꺼내 각각의 상자에 저장
                        int id = rs.getInt("id");             // id 칸 → 정수
                        String title = rs.getString("title"); // title 칸 → 문자열
                        String body = rs.getString("body");   // body 칸 → 문자열

                        // 세 값을 띄어쓰기(" ")로 이어붙여서 출력
                        // 예: "5 jdbc-flow verified"
                        System.out.println(
                                id + " " + title + " " + body
                        );
                    }
                }
            }
        }
    }
}