package racing;

/*
 * [역할] 상금 규약. "순위가 정해진 뒤, 그 순위의 상금은 얼마인가"만 약속한다.
 *
 * [RaceStrategy 와 인터페이스를 나눈 이유]
 * RaceStrategy.execute(round) 는 그 라운드의 주행 점수이다.
 * prizeOf(rank) 는 최종 순위가 나온 뒤의 상금이다.
 * 받는 숫자가 둘 다 int 여도 의미가 다르다. 라운드 3과 순위 3은 같은 자리가 아니다.
 * 주행 점수를 바꾸는 이유(전략표)와 상금 표를 바꾸는 이유는 다르다.
 * 한 인터페이스에 두 메서드를 넣으면, 상금만 다른 클래스도 주행 메서드까지 구현해야 한다.
 *
 * [누가 호출하나]
 * 컨트롤러가 3라운드 합계로 최종 순위를 매긴 다음, 그 순위만 이 메서드에 넘긴다.
 * 라운드 줄("1위 번개 / ... / 40")에는 상금을 붙이지 않는다.
 * 최종 줄에만 "상금 100"처럼 이 반환값이 들어간다.
 *
 * [구현은 따로 둔다]
 * 이 파일에는 상금 if 가 없다. 표의 숫자는 OfficialPrizeCalculator 에 있다.
 * 컨트롤러 필드의 컴파일 타입은 이 인터페이스이고,
 * Main 이 실제로 넣는 객체는 OfficialPrizeCalculator 이다.
 * 전략 클래스(FrontRunner 등) 안에 상금 if 를 넣지 않는다.
 *
 * [람다로도 적을 수 있지만 경주 경로에서는 쓰지 않는다]
 * 추상 메서드가 하나인 인터페이스는 람다로 몸통만 적을 수 있다.
 * 예) PrizeCalculator onlyFirst = rank -> rank == 1 ? 100 : 0;
 * 그 람다를 경주에 쓰면 OfficialPrizeCalculator 의 @Override 연습이 사라진다.
 * 경주가 쓰는 상금은 구현 클래스이다.
 */
public interface PrizeCalculator {

    /*
     * 최종 순위 하나를 받아 상금을 돌려준다.
     * 1위 100, 2위 40, 3위 20, 그 외 0.
     * 몸통은 여기 없고 OfficialPrizeCalculator.prizeOf 에 있다.
     */
    int prizeOf(int rank);
}
