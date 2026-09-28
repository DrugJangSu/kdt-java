package racing;

/*
 * [역할] 주행 전략 4번. 화면 이름 "균속".
 *
 * [상속 관계]
 * Pacer → AbstractRaceStrategy → 규약 RaceStrategy
 * 자식이 채우는 칸은 run 뿐이다. label() 을 여기서 다시 쓰지 않는다.
 * 화면 이름은 부모 필드이고, 생성자의 super("균속") 으로 들어간다.
 *
 * [점수표]  라운드마다 2점씩 오른다.
 *   1라운드 24
 *   2라운드 26
 *   3라운드 28
 *   그 외   0
 *
 * [전략 체험(메뉴 7)에서의 위치]
 * 네 구현을 한 목록에 이 순서로 담는다.
 *   new FrontRunner()  선행 40
 *   new Closer()       추입 16
 *   new Marker()       선입 30
 *   new Pacer()        균속 24   ← 이 클래스의 execute(1)
 *
 * 반복문의 변수 타입은 RaceStrategy 이다.
 * 그래서 그 자리에서 호출할 수 있는 것은 execute 와 label 뿐이다.
 * 1라운드 점수가 40, 16, 30, 24 로 다른 이유는
 * 같은 execute 가 각 객체의 run 으로 갈라지기 때문이다.
 * 이 목록을 람다나 Map 으로 바꾸지 않는다. 구현 객체를 그대로 담는다.
 *
 * [생성자]
 * 인자를 받지 않는다. StrategyService.choose(4) 가 new Pacer() 를 만든다.
 * 전략 번호가 1~4 가 아니면 이 객체를 만들지 않고 InvalidStrategyException 이 난다.
 * 그 예외는 아직 이 단계의 파일이 아니고, 이후 단계에서 추가한다.
 */
public class Pacer extends AbstractRaceStrategy {

    public Pacer() {
        super("균속");
    }

    /* AbstractRaceStrategy.run 을 구현한다. 1→24, 2→26, 3→28. 그 외 라운드는 0. */
    @Override
    protected int run(int round) {
        if (round == 1) {
            return 24;
        } else if (round == 2) {
            return 26;
        } else if (round == 3) {
            return 28;
        } else {
            return 0;
        }
    }
}
