public interface RaceStrategy { // interface = "이 메서드들은 꼭 있어야 한다"는 약속(규칙서). 내용은 없음
    int execute(int round);     // 전략을 실행하는 메서드. round(라운드 번호)를 받아서 int(결과 속도 등)를 돌려줌
    String label();             // 전략 이름표를 돌려주는 메서드 (예: "추입", "선행")
}