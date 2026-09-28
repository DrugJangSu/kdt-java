package racing;

/*
 * [역할] 주행 전략 1번. 화면 이름 "선행".
 *
 * [상속 관계]
 * FrontRunner → AbstractRaceStrategy → 규약 RaceStrategy
 * 이 클래스가 구현하는 메서드는 run 하나뿐이다.
 * execute 와 label 은 부모가 이미 구현했고, 여기서 다시 쓰지 않는다.
 * execute 는 final 이라 다시 쓸 수도 없다.
 *
 * [점수표]  초반에 빠르고 후반에 떨어진다.
 *   1라운드 40
 *   2라운드 28
 *   3라운드 16
 *   그 외   0
 * 경주는 항상 1, 2, 3라운드만 돈다. else 의 0 은 그 범위 밖을 받은 경우이다.
 *
 * [이 객체가 쓰이는 곳]
 * - StrategyService.choose(1) 이 new FrontRunner() 를 돌려준다.
 *   반환 타입은 FrontRunner 가 아니라 RaceStrategy 이다.
 * - 메뉴 7 전략 체험은 ArrayList<RaceStrategy> 에 이 객체를 첫 번째로 넣는다.
 *   strategy.execute(1) 이 40 이 되는 이유가 아래 run 이다.
 *
 * [호출 흐름 예]  서울 1경주, 번개, 강기수, 전략 1
 *   choose(1) → FrontRunner
 *   execute(1) → prepare → run(1) → 40 → finish(40) → 40
 *   execute(2) → 28
 *   execute(3) → 16
 *   세 라운드 합계 84. 미리보기에서 번개가 2위, 상금 40 인 합계가 이 값이다.
 *   상금 if 는 이 클래스에 넣지 않는다. 상금은 PrizeCalculator 의 책임이다.
 *
 * [생성자가 인자를 받지 않는 이유]
 * "선행"은 이 클래스의 고정된 화면 이름이다.
 * 호출하는 쪽이 이름을 넘기면 다른 문자열이 섞일 수 있다.
 * 이름은 super("선행") 으로 부모 필드에만 전달하고, label() 은 부모가 돌려준다.
 *
 * [@Override]
 * 컴파일러에게 "부모의 run(int) 을 재정의한다"고 알린다.
 * 이름을 runs 로 잘못 쓰거나 매개변수를 String 으로 바꾸면,
 * 이 애너테이션이 있는 줄에서 재정의가 아니라고 바로 알려 준다.
 * 애너테이션이 없으면 오타 메서드는 새 메서드로 남고,
 * "추상 run(int) 를 구현하지 않았다"는 오류는 클래스 선언 쪽에 표시될 수 있다.
 *
 * [조건은 if ~ else if ~ else 만 쓴다]
 * 이 과제는 switch 를 쓰지 않는다.
 */
public class FrontRunner extends AbstractRaceStrategy {

    public FrontRunner() {
        super("선행");
    }

    /*
     * AbstractRaceStrategy.run 을 구현한다.
     * execute 가 run 을 부르면, 실제 객체가 FrontRunner 일 때 이 몸통이 실행된다.
     * 부모 execute 는 이 반환값을 finish 에 넘기고, finish 는 그 값을 그대로 돌려준다.
     */
    @Override
    protected int run(int round) {
        if (round == 1) {
            return 40;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 16;
        } else {
            return 0;
        }
    }
}
