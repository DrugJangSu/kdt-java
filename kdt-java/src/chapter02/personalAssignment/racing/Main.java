package racing;

import java.util.Scanner;

/*
 * [역할] 프로그램의 유일한 실행 입구이다. 객체를 만들어 연결하고 run() 만 호출한다.
 *
 * [Main 과 RaceController 를 나눈 이유]
 * 메뉴 문장을 고치는 일과, 어떤 저장소·상금 구현을 쓸지 정하는 일은 같이 바뀌지 않는다.
 * 조립을 여기에 두면 컨트롤러는 이미 받은 서비스의 메서드만 호출한다.
 * 컨트롤러는 저장소를 new 하지 않고, main 도 두지 않는다.
 *
 * [만드는 순서]
 * 1. 저장소 세 개. 말, 기수, 경주는 각각 자신의 HashMap 을 가진다.
 * 2. 서비스. HorseService, JockeyService, RaceService 는 저장소를 하나씩 생성자로 받는다.
 *    StrategyService 는 저장소가 없어 인자가 없다.
 * 3. 상금은 PrizeCalculator 변수에 OfficialPrizeCalculator 를 담는다.
 *    컨트롤러는 공식 구현 클래스 이름이 아니라 인터페이스로 prizeOf 를 호출한다.
 * 4. Scanner 는 콘솔 입력이다. 컨트롤러가 메뉴와 이름, 번호를 읽는다.
 * 5. 위 객체를 RaceController 생성자에 넘기고 run() 으로 메뉴 반복을 시작한다.
 *
 * [이 메서드가 하지 않는 일]
 * 말 등록, 순위 계산, 예외 메시지 출력은 컨트롤러와 서비스에 있다.
 * main 은 조립이 끝나면 더 이상 경주 규칙을 모른다.
 */
public class Main {
    public static void main(String[] args) {
        HorseRepository horseRepository = new HorseRepository();
        JockeyRepository jockeyRepository = new JockeyRepository();
        RaceRepository raceRepository = new RaceRepository();

        HorseService horseService = new HorseService(horseRepository);
        JockeyService jockeyService = new JockeyService(jockeyRepository);
        RaceService raceService = new RaceService(raceRepository);
        StrategyService strategyService = new StrategyService();
        PrizeCalculator prizeCalculator = new OfficialPrizeCalculator();
        Scanner scanner = new Scanner(System.in);

        RaceController controller = new RaceController(
                horseService,
                jockeyService,
                raceService,
                strategyService,
                prizeCalculator,
                scanner);
        controller.run();
    }
}
