package racing;

/*
 * [역할] 주행 전략 3번. 화면 이름 "선입".
 *
 * [상속 관계]
 * Marker → AbstractRaceStrategy → 규약 RaceStrategy
 * run 만 구현한다. 실행 순서는 부모 execute 가 prepare → run → finish 로 고정한다.
 *
 * [점수표]  세 라운드가 모두 같다.
 *   1라운드 30
 *   2라운드 30
 *   3라운드 30
 *   그 외   0
 *
 * [최종 시나리오에서 이 점수가 쓰이는 곳]
 * 부산 2경주: 질주/오기수/선행(FrontRunner)  vs  버팀/박기수/선입(이 클래스)
 *
 *   1라운드  선행 40, 선입 30  → 질주 1위, 버팀 2위
 *   2라운드  선행 28, 선입 30  → 버팀이 앞으로
 *   3라운드  선행 16, 선입 30  → 버팀 유지
 *   합계     선입 90, 선행 84  → 버팀 1위 상금 100, 질주 2위 상금 40
 *
 * 매 라운드 30이 같은 것은 의도이다. if 세 분기를 모두 적어 표와 코드를 1:1로 맞춘다.
 *
 * [생성자]
 * 인자를 받지 않는다. super("선입") 만 부모에 넘긴다.
 * choose(3) 과 전략 체험 목록의 세 번째 원소가 new Marker() 이다.
 * 체험 1라운드 출력 줄은 "선입 30" 이다.
 */
public class Marker extends AbstractRaceStrategy {

    public Marker() {
        super("선입");
    }

    /* AbstractRaceStrategy.run 을 구현한다. 1, 2, 3라운드 모두 30이다. */
    @Override
    protected int run(int round) {
        if (round == 1) {
            return 30;
        } else if (round == 2) {
            return 30;
        } else if (round == 3) {
            return 30;
        } else {
            return 0;
        }
    }
}
