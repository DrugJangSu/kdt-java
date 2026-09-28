package racing;

/*
 * [역할] 전략 번호 하나를 주행 전략 객체로 바꾸는 서비스이다.
 *
 * [저장소가 없는 이유]
 * 전략은 번호로 꺼내 오는 저장 대상이 아니다.
 * 호출할 때마다 FrontRunner, Closer, Marker, Pacer 중 하나를 새로 만든다.
 * 네 클래스는 필드로 기억할 상태가 없고, run 이 라운드 번호만으로 점수를 정한다.
 * 그래서 생성자 인자가 없다. Main 은 new StrategyService() 만 한다.
 *
 * [Map 으로 고르지 않는 이유]
 * Map<Integer, RaceStrategy> 에 미리 담아 두면 번호와 객체가 한 곳에 모인다.
 * 이 과제는 그 방식을 쓰지 않는다. if ~ else if ~ else 로 번호와 클래스를 연결한다.
 * switch 도 쓰지 않는다.
 *
 * [반환 타입이 RaceStrategy 인 이유]
 * 호출하는 쪽은 FrontRunner 인지 Closer 인지 변수에 적지 않는다.
 * 컴파일 타입은 인터페이스 하나이고, 실행 객체만 번호에 따라 달라진다.
 * 그 객체의 execute 는 부모의 final 몸통이고, 점수는 각 클래스의 run 으로 간다.
 *
 * [번호와 클래스]
 *   1 선행 FrontRunner  1라운드 40, 2라운드 28, 3라운드 16
 *   2 추입 Closer       1라운드 16, 2라운드 28, 3라운드 42
 *   3 선입 Marker       세 라운드 모두 30
 *   4 균속 Pacer        1라운드 24, 2라운드 26, 3라운드 28
 *   그 외 InvalidStrategyException "전략은 1, 2, 3, 4입니다."
 *
 * [참가 등록에서의 위치]
 * 컨트롤러는 경주, 말, 기수를 찾은 다음에 choose 를 호출한다.
 * 이 메서드가 예외를 던지면 enter 는 호출되지 않고 참가 수는 늘지 않는다.
 * 경주 시작 때는 저장된 전략 번호로 choose 를 다시 호출해 execute(라운드) 를 돌린다.
 */
public class StrategyService {

    /*
     * 1~4 가 아니면 전략 객체를 만들지 않는다.
     * 예외 문장에는 잘못된 번호를 붙이지 않는다. 허용 범위만 알려 준다.
     */
    public RaceStrategy choose(int choice) {
        if (choice == 1) {
            return new FrontRunner();
        } else if (choice == 2) {
            return new Closer();
        } else if (choice == 3) {
            return new Marker();
        } else if (choice == 4) {
            return new Pacer();
        } else {
            throw new InvalidStrategyException("전략은 1, 2, 3, 4입니다.");
        }
    }
}
