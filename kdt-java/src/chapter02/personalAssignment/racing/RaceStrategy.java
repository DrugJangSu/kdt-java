package racing;

/*
 * [역할] 주행 전략의 규약. "이 메서드가 있다"만 약속하고, 점수 계산 몸통은 적지 않는다.
 *
 * [메서드가 두 개인 이유]
 * - execute(round) : 그 라운드의 주행 점수. 라운드는 1, 2, 3 만 의미 있다. 그 외는 0점.
 * - label()        : 화면에 찍을 전략 이름. "선행", "추입", "선입", "균속"
 *
 * 점수와 이름은 같이 필요하지만, 계산 규칙과 표시 이름은 다른 일이다.
 * 컨트롤러는 FrontRunner, Closer 같은 클래스 이름을 직접 쓰지 않고
 * 이 인터페이스 타입으로만 두 메서드를 호출한다.
 *
 * [다형성이 일어나는 지점]
 *   RaceStrategy strategy = new Closer();
 *   strategy.execute(3);   // 변수 타입은 RaceStrategy, 실제 객체는 Closer → 42
 *
 * 컴파일러가 이 자리에서 허용하는 호출은 execute 와 label 뿐이다.
 * 실제 점수가 전략마다 다른 이유는 execute 안의 run() 이
 * 실행 시점의 객체(FrontRunner, Closer, Marker, Pacer)로 연결되기 때문이다.
 * 그 연결 순서를 고정하는 코드는 AbstractRaceStrategy 에 있다.
 *
 * [PrizeCalculator 와 인터페이스를 나눈 이유]
 * RaceStrategy 는 "이 라운드에 몇 점으로 달렸는가"이다.
 * PrizeCalculator 는 "최종 순위가 정해진 뒤 상금이 얼마인가"이다.
 * 상금 표를 바꿀 이유와 주행 점수를 바꿀 이유는 다르다.
 * 한 인터페이스에 prizeOf 와 execute 를 같이 넣으면,
 * 상금만 다른 클래스도 주행 메서드를 구현해야 하고, 순위와 라운드가 같은 숫자처럼 섞인다.
 *
 * [이 인터페이스만으로는 실행 순서를 잠글 수 없다]
 * prepare → run → finish 순서를 하위 클래스가 통째로 갈아끼우지 못하게 하려면
 * execute 를 final 로 둬야 한다.
 * 인터페이스 메서드는 final 이 되지 않는다.
 * 그래서 규약은 이 인터페이스에 두고, 잠긴 순서는 추상 클래스에 둔다.
 */
public interface RaceStrategy {

    /*
     * 라운드 번호를 받아 그 라운드의 점수를 돌려준다.
     * 구현 몸통은 인터페이스에 없다. AbstractRaceStrategy.execute 가 순서를 고정하고,
     * 점수 숫자 자체는 각 전략의 run 이 정한다.
     */
    int execute(int round);

    /* 화면용 전략 이름. 참가 성공 문장과 순위 줄, 전략 체험 줄에서 쓴다. */
    String label();
}
