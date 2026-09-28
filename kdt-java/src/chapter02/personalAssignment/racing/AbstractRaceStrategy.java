package racing;

/*
 * [역할] 템플릿 메서드. 네 전략이 공통으로 따르는 실행 순서를 여기 한 곳에 고정한다.
 *
 * [왜 인터페이스가 아니라 추상 클래스인가]
 * 인터페이스는 "execute 와 label 이 있다"는 규약이다.
 * 추상 클래스는 그 규약을 구현하면서, 이미 적어 둔 몸통과 잠근 메서드를 자식에게 물려 준다.
 *
 * execute 를 final 로 잠그려면 final 을 붙일 수 있는 타입이 필요하다.
 * 인터페이스의 메서드는 final 이 될 수 없다.
 * final 을 빼면 어떤 전략이 execute 를 통째로 다시 써서
 * prepare / run / finish 순서를 건너뛰고 상수만 돌려도 컴파일은 된다.
 * 그 순간 "순서를 지키라"는 설계가 깨진다.
 *
 * [템플릿 메서드 패턴이란]
 * 부모가 전체 순서를 메서드 하나로 박아 두고,
 * 자식은 그 순서 중 달라져야 하는 칸만 채우는 방식이다.
 *
 *   execute (final, 순서 고정, 자식이 다시 못 씀)
 *     1. prepare()           공통. 이번 과제는 빈 몸통
 *     2. pace = run(round)   여기만 전략마다 다름. 자식이 구현
 *     3. return finish(pace) 공통. 이번 과제는 받은 점수를 그대로 반환
 *
 * 그래서 이번 과제의 execute(라운드) 결과는 run(라운드) 결과와 같다.
 * prepare 와 finish 를 abstract 로 두면 네 전략이 같은 빈 몸통을 네 번 적게 된다.
 * 공통 몸통은 부모에 한 번만 둔다.
 *
 * [동적 바인딩은 execute 가 아니라 run 에서 일어난다]
 * execute 는 final 이라 몸통이 이 클래스 하나로 고정된다.
 * 그 몸통이 호출하는 run() 은 abstract 이고 자식이 다시 썼다.
 * 변수 타입이 RaceStrategy 여도, 실제 객체가 Closer 이면 Closer.run 이 실행된다.
 *
 *   RaceStrategy strategy = new Closer();
 *   strategy.execute(3);
 *     → AbstractRaceStrategy.execute 몸통 진입
 *     → prepare()
 *     → run(3) 이 Closer.run 으로 연결 → 42
 *     → finish(42) → 42 반환
 *
 * [new 로 직접 만들 수 없다]
 * run 이 아직 abstract 라서 이 클래스 자체는 미완성이다.
 * 미완성 클래스는 abstract 여야 하고, new AbstractRaceStrategy("선행") 은 컴파일되지 않는다.
 * 객체는 FrontRunner, Closer, Marker, Pacer 로만 만든다.
 *
 * [label 을 부모가 구현하는 이유]
 * 화면 이름은 전략마다 다르지만, "필드를 저장했다가 돌려준다"는 방법은 같다.
 * 자식은 생성자에서 super("선행") 처럼 이름만 넘기고, label() 은 다시 쓰지 않는다.
 * 그래서 이름으로 갈라지는 코드는 여기 한 곳이다.
 */
public abstract class AbstractRaceStrategy implements RaceStrategy {
    private final String label;

    /*
     * protected 생성자: 이 패키지의 자식 클래스만 super(...) 로 호출한다.
     * public 으로 열어 두면 외부에서 추상 클래스 생성 흐름을 직접 다루는 것처럼 보인다.
     * 추상 클래스라 new 는 어차피 불가능하고, 자식 생성자가 이름을 넘기는 입구이다.
     */
    protected AbstractRaceStrategy(String label) {
        this.label = label;
    }

    /*
     * RaceStrategy.execute 를 구현한다.
     * final: FrontRunner 등이 execute 라는 이름으로 이 메서드를 다시 쓸 수 없다.
     * 순서는 prepare → run → finish 이고, finish 가 돌려준 값을 그대로 return 한다.
     */
    @Override
    public final int execute(int round) {
        prepare();
        int pace = run(round);
        return finish(pace);
    }

    /*
     * 주행 전에 공통으로 할 일. 이번 과제에서는 할 일이 없어 몸통이 비어 있다.
     * 비어 있어도 호출 순서는 유지한다. 나중에 공통 준비가 생겨도 execute 는 그대로 둔다.
     * abstract 가 아니므로 자식이 꼭 다시 쓸 필요는 없다.
     */
    protected void prepare() {
    }

    /*
     * 전략마다 달라지는 유일한 칸. 선언만 있고 몸통이 없다.
     * 세미콜론으로 끝나고 중괄호가 없다. 중괄호를 붙이면 추상 메서드가 아니게 된다.
     * protected: 부모 execute 와 같은 상속 계통에서만 호출한다. 컨트롤러는 run 을 직접 부르지 않고 execute 를 부른다.
     * 자식은 이 메서드만 @Override 로 구현하면 객체를 만들 수 있다.
     */
    protected abstract int run(int round);

    /*
     * 주행 후에 점수를 확정하는 공통 칸. 이번 과제는 보정 없이 pace 를 그대로 돌려준다.
     * 그래서 execute 의 반환값은 run 의 반환값과 같다.
     */
    protected int finish(int pace) {
        return pace;
    }

    /*
     * RaceStrategy.label 을 구현한다.
     * 자식이 다시 쓰지 않으므로, 화면 이름은 생성자로 받은 필드 하나로 결정된다.
     */
    @Override
    public String label() {
        return label;
    }
}
