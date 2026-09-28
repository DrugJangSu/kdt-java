package racing;

/*
 * [역할] 주행 전략 2번. 화면 이름 "추입".
 *
 * [상속 관계]
 * Closer → AbstractRaceStrategy → 규약 RaceStrategy
 * 이 클래스는 run 만 구현한다. execute, label, prepare, finish 는 부모 것을 그대로 쓴다.
 *
 * [점수표]  후반에 따라붙는다. 3라운드가 가장 크다.
 *   1라운드 16
 *   2라운드 28
 *   3라운드 42
 *   그 외   0
 *
 * [미리보기에서 이 점수가 순위를 뒤집는 과정]
 * 막차 / 한기수 / 추입  vs  번개 / 강기수 / 선행(FrontRunner)
 *
 *   1라운드  추입 16, 선행 40  → 선행이 앞
 *   2라운드  둘 다 28         → 점수가 같으면 먼저 참가한 선행(번개)이 1위로 남는다
 *   3라운드  추입 42, 선행 16 → 추입이 앞으로 나간다
 *   합계     추입 16+28+42=86, 선행 40+28+16=84
 *   최종     막차 1위 상금 100, 번개 2위 상금 40
 *
 * 2라운드가 둘 다 28인 것은 버그가 아니다. 동점 규칙은 순위 계산 쪽에 있다.
 * 이 클래스는 "이번 라운드 점수"만 돌려주고, 누구를 앞에 둘지는 결정하지 않는다.
 *
 * [동적 바인딩 확인 예]
 *   RaceStrategy strategy = new Closer();
 *   strategy.execute(3);  // 42
 * execute 몸통은 AbstractRaceStrategy 에 있다.
 * 그 안의 run() 호출이 실행 시점의 실제 객체인 Closer 의 이 메서드로 연결된다.
 * 변수 타입을 FrontRunner 로만 두면 Closer 를 같은 코드로 돌릴 수 없다.
 *
 * [생성자]
 * 인자를 받지 않는다. 화면 이름 "추입"은 super 로 부모 label 필드에 고정한다.
 * StrategyService.choose(2) 와 메뉴 7이 new Closer() 로 만든다.
 */
public class Closer extends AbstractRaceStrategy {

    public Closer() {
        super("추입");
    }

    /*
     * AbstractRaceStrategy.run 을 구현한다.
     * round 가 3일 때만 42이다. 상금 계산은 여기 두지 않는다.
     */
    @Override
    protected int run(int round) {
        if (round == 1) {
            return 16;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 42;
        } else {
            return 0;
        }
    }
}
