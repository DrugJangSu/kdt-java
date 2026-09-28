package racing;

/*
 * [역할] PrizeCalculator 의 공식 상금표.
 *
 * [점수표]
 *   순위 1 → 100
 *   순위 2 →  40
 *   순위 3 →  20
 *   그 외  →   0
 *
 * [호출 흐름]
 * 경주 시작(메뉴 5)에서 라운드 점수는 RaceStrategy.execute 가 만든다.
 * 세 라운드 합계로 순위가 정해진 뒤에만 이 객체의 prizeOf(순위) 를 호출한다.
 *
 *   막차 합계 86 → 1위 → prizeOf(1) → 100
 *   번개 합계 84 → 2위 → prizeOf(2) → 40
 *
 * 화면 문장은 "1위 막차 / 한기수 / 추입 / 합계 86 / 상금 100" 처럼
 * 컨트롤러가 getter 와 이 반환값을 이어 붙인다. 이 클래스에 toString 은 없다.
 *
 * [다형성]
 * 컨트롤러가 들고 있는 변수의 컴파일 타입은 PrizeCalculator 이다.
 * 실행되는 객체는 이 클래스이다.
 * prizeOf 를 호출하면 이 몸통이 실행된다.
 *
 * [조건은 if ~ else if ~ else]
 * 이 과제는 switch 를 쓰지 않는다.
 * main 은 두지 않는다. 실행 진입점은 나중에 Main.main 하나이다.
 */
public class OfficialPrizeCalculator implements PrizeCalculator {

    /*
     * PrizeCalculator.prizeOf 를 구현한다.
     * rank 는 1부터 세는 최종 순위이다. 라운드 번호가 아니다.
     */
    @Override
    public int prizeOf(int rank) {
        if (rank == 1) {
            return 100;
        } else if (rank == 2) {
            return 40;
        } else if (rank == 3) {
            return 20;
        } else {
            return 0;
        }
    }
}
