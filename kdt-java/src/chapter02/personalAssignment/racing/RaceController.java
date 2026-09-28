package racing;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * [역할] 입력·출력과 여러 서비스 호출을 순서대로 조립한다.
 * 이번 과제에서는 경주 진행, 라운드 순위, 최종 순위, 상금 호출, 기록 저장도 여기서 한다.
 *
 * [필드에 있는 것]
 * 서비스 네 개, PrizeCalculator, Scanner.
 * 저장소 필드는 없다. new HorseRepository 같은 코드도 없다.
 * 저장소를 만드는 일은 Main 이다.
 *
 * [필드에 없는 이유]
 * 참가 등록은 한 서비스가 말·기수·경주를 한꺼번에 알지 않는다.
 * 컨트롤러가 경주 → 말 → 기수 → 전략 순으로 서비스를 부르고,
 * 이름과 전략 번호만 RaceService.enter 에 넘긴다.
 *
 * [메뉴가 부르는 메서드]
 * 1 registerHorse   HorseService.register
 * 2 registerJockey  JockeyService.register
 * 3 createRace      RaceService.create
 * 4 enter           조회 네 번 뒤 RaceService.enter
 * 5 startRace       전략 실행, 순위, 상금, 기록 저장
 * 6 showRecords     RaceService.recordsOf
 * 7 demonstrate     ArrayList<RaceStrategy> 순회
 * 0                 "경주를 마칩니다."
 * 그 외             "없는 번호입니다."
 *
 * [예외는 한 칸으로 받는다]
 * 이름과 번호를 모두 읽은 뒤에 서비스 호출을 try 에 넣는다.
 * catch (IllegalArgumentException) 으로 메시지만 출력한다.
 * 빈 이름, 말 없음, 기수 없음, 경주 없음, 전략 번호 오류, 참가 2마리 미만이
 * 모두 이 부모 타입이거나 그 자식이다.
 * main 은 이 클래스에 없다.
 */
public class RaceController {
    private final HorseService horseService;
    private final JockeyService jockeyService;
    private final RaceService raceService;
    private final StrategyService strategyService;
    private final PrizeCalculator prizeCalculator;
    private final Scanner scanner;

    /*
     * 여섯 객체를 모두 밖에서 받는다.
     * 상금 필드의 타입은 OfficialPrizeCalculator 가 아니라 PrizeCalculator 이다.
     * 최종 순위에서 prizeOf 만 호출하면 되고, 상금 표의 if 는 구현 클래스에 있다.
     */
    public RaceController(HorseService horseService, JockeyService jockeyService,
                          RaceService raceService, StrategyService strategyService,
                          PrizeCalculator prizeCalculator, Scanner scanner) {
        this.horseService = horseService;
        this.jockeyService = jockeyService;
        this.raceService = raceService;
        this.strategyService = strategyService;
        this.prizeCalculator = prizeCalculator;
        this.scanner = scanner;
    }

    /*
     * 메뉴를 한 번 처리하고, 종료(0)가 아니면 메뉴를 다시 찍는다.
     * going 이 false 가 되어야 반복이 끝난다.
     *
     * nextInt 는 숫자만 가져가고, 사용자가 친 엔터는 입력에 남는다.
     * 바로 이어서 nextLine 으로 그 엔터를 버린다.
     * 이 한 줄이 없으면 메뉴 1에서 말 이름을 읽기도 전에 빈 문자열이 들어가
     * "말 이름이 비어 있습니다." 가 된다.
     * 참가 등록처럼 숫자를 여러 번 이어서 읽을 때는, 각 메서드 안에서
     * 마지막 nextInt 다음에 nextLine 을 한 번만 호출한다.
     */
    public void run() {
        boolean going = true;
        while (going) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerHorse();
            } else if (menu == 2) {
                registerJockey();
            } else if (menu == 3) {
                createRace();
            } else if (menu == 4) {
                enter();
            } else if (menu == 5) {
                startRace();
            } else if (menu == 6) {
                showRecords();
            } else if (menu == 7) {
                demonstrate();
            } else if (menu == 0) {
                System.out.println("경주를 마칩니다.");
                going = false;
            } else {
                System.out.println("없는 번호입니다.");
            }
        }
    }

    /*
     * 번호를 물을 때마다 이 블록 전체를 다시 찍는다.
     * 프롬프트 "번호> " 끝에는 공백 한 칸이 있다. println 이 아니라 print 라서
     * 사용자가 치는 숫자가 같은 줄에 붙는다.
     */
    private void printMenu() {
        System.out.println("🏇 서울경마공원");
        System.out.println("1. 말 등록");
        System.out.println("2. 기수 등록");
        System.out.println("3. 경주 만들기");
        System.out.println("4. 참가 등록");
        System.out.println("5. 경주 시작");
        System.out.println("6. 기록 보기");
        System.out.println("7. 전략 체험");
        System.out.println("0. 종료");
        System.out.println("전략은 1선행 2추입 3선입 4균속");
        System.out.print("번호> ");
    }

    /*
     * 이름을 먼저 읽고, 그 다음 try 에서 등록한다.
     * 성공 문장은 Horse 가 찍지 않는다. getter 로 조립한다.
     * "🐴 말을 등록했습니다. 번호 1, 번개"
     * 실패하면 예외 메시지만 찍고 메뉴로 돌아간다. 번호는 서비스가 증가시키지 않는다.
     */
    private void registerHorse() {
        System.out.print("말 이름> ");
        String name = scanner.nextLine();
        try {
            Horse horse = horseService.register(name);
            System.out.println("🐴 말을 등록했습니다. 번호 " + horse.getId() + ", " + horse.getName());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /* 기수 등록. 성공 문장은 "👤 기수를 등록했습니다. 번호 1, 강기수" 형식이다. */
    private void registerJockey() {
        System.out.print("기수 이름> ");
        String name = scanner.nextLine();
        try {
            Jockey jockey = jockeyService.register(name);
            System.out.println("👤 기수를 등록했습니다. 번호 " + jockey.getId() + ", " + jockey.getName());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /* 경주 만들기. 성공 문장은 "🏁 경주를 만들었습니다. 번호 1, 서울 1경주" 형식이다. */
    private void createRace() {
        System.out.print("경주 이름> ");
        String title = scanner.nextLine();
        try {
            Race race = raceService.create(title);
            System.out.println("🏁 경주를 만들었습니다. 번호 " + race.getId() + ", " + race.getTitle());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
     * [참가 등록 흐름]
     * 네 번호를 모두 읽은 뒤에 검사한다. 실패해도 질문은 네 번 이미 끝난 상태이다.
     * 숫자를 네 번 이어서 읽으므로 nextLine 은 마지막 nextInt 다음에 한 번만 호출한다.
     *
     * 검사 순서. 앞에서 실패하면 뒤는 호출하지 않는다.
     *   1. RaceService.findById(raceId)     경주가 있는가
     *   2. HorseService.findById(horseId)   말이 있는가
     *   3. JockeyService.findById(jockeyId) 기수가 있는가
     *   4. StrategyService.choose(choice)   전략 번호가 1~4 인가
     *   5. RaceService.enter(경주번호, 말 이름, 기수 이름, 전략 번호)
     *
     * enter 에는 Horse 객체, Jockey 객체를 넘기지 않는다.
     * 경주는 이름과 전략 번호만 기억한다.
     * 성공 문장의 전략 이름은 strategy.label() 이다.
     * "🎫 참가했습니다. 서울 1경주 / 번개 / 강기수 / 선행"
     */
    private void enter() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        System.out.print("말 번호> ");
        int horseId = scanner.nextInt();
        System.out.print("기수 번호> ");
        int jockeyId = scanner.nextInt();
        System.out.print("전략 번호> ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            Race race = raceService.findById(raceId);
            Horse horse = horseService.findById(horseId);
            Jockey jockey = jockeyService.findById(jockeyId);
            RaceStrategy strategy = strategyService.choose(choice);
            raceService.enter(raceId, horse.getName(), jockey.getName(), choice);
            System.out.println("🎫 참가했습니다. " + race.getTitle() + " / " + horse.getName()
                    + " / " + jockey.getName() + " / " + strategy.label());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
     * [경주 실행 흐름]
     * 1. 경주를 찾는다. 없으면 예외 메시지만 찍고 기록은 건드리지 않는다.
     * 2. 참가가 2마리 미만이면 "참가 말이 2마리 미만입니다." 만 찍는다.
     *    이 경우에는 clearRecords 를 호출하지 않는다.
     *    먼저 지우면, 시작에 실패했는데도 이전에 잘 달린 기록이 사라진다.
     * 3. 2마리 이상이면 기록을 비운 뒤 라운드 1, 2, 3 만 돈다.
     *
     * [두 개의 점수 목록]
     * scores : 이번 라운드에서 참가 순서대로 쌓은 점수. 라운드가 바뀌면 새로 만든다.
     * totals : 세 라운드 합계. 경주 시작 때 0으로 만들고, 라운드마다 더한다.
     * 라운드 줄에 찍는 숫자는 scores 이다. "합계"와 "상금"은 붙이지 않는다.
     * 합계와 상금은 3라운드가 끝난 뒤 최종 순위에만 쓴다.
     *
     * [한 참가의 점수]
     * strategyChoiceAt(i) 로 저장해 둔 번호(1~4)를 읽고 choose 한다.
     * 변수 타입은 RaceStrategy 이다. execute(round) 가
     * prepare → 그 객체의 run → finish 순서로 점수를 돌려준다.
     *
     * [출력과 저장]
     * save 가 같은 문장을 화면에 찍고 addRecord 로 남긴다.
     * 메뉴 6은 그 문장을 같은 순서로 다시 찍는다.
     */
    private void startRace() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        scanner.nextLine();
        try {
            Race race = raceService.findById(raceId);
            if (race.entryCount() < 2) {
                throw new IllegalArgumentException("참가 말이 2마리 미만입니다.");
            }
            raceService.clearRecords(raceId);
            ArrayList<Integer> totals = new ArrayList<>();
            for (int i = 0; i < race.entryCount(); i++) {
                totals.add(0);
            }
            for (int round = 1; round <= 3; round++) {
                ArrayList<Integer> scores = new ArrayList<>();
                for (int i = 0; i < race.entryCount(); i++) {
                    RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(i));
                    int pace = strategy.execute(round);
                    scores.add(pace);
                    totals.set(i, totals.get(i) + pace);
                }
                ArrayList<Integer> order = rank(scores);
                save(raceId, "🏁 " + race.getTitle() + " " + round + "라운드");
                for (int place = 0; place < order.size(); place++) {
                    int index = order.get(place);
                    RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(index));
                    String line = (place + 1) + "위 " + race.horseNameAt(index) + " / "
                            + race.jockeyNameAt(index) + " / " + strategy.label() + " / "
                            + scores.get(index);
                    save(raceId, line);
                }
            }
            ArrayList<Integer> finalOrder = rank(totals);
            save(raceId, "🏆 최종 순위");
            for (int place = 0; place < finalOrder.size(); place++) {
                int index = finalOrder.get(place);
                int rankNo = place + 1;
                RaceStrategy strategy = strategyService.choose(race.strategyChoiceAt(index));
                int prize = prizeCalculator.prizeOf(rankNo);
                String line = rankNo + "위 " + race.horseNameAt(index) + " / "
                        + race.jockeyNameAt(index) + " / " + strategy.label() + " / 합계 "
                        + totals.get(index) + " / 상금 " + prize;
                save(raceId, line);
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
     * 같은 문장을 화면과 경주 기록에 함께 남긴다.
     * 기록을 먼저 지우는지 여부는 여기서 결정하지 않는다. startRace 가 참가 수를 확인한 뒤에만 이 메서드로 들어온다.
     */
    private void save(int raceId, String line) {
        System.out.println(line);
        raceService.addRecord(raceId, line);
    }

    /*
     * 점수가 큰 참가를 앞으로 보낸다.
     * 점수가 같으면 참가 목록에서 더 앞인 말(인덱스가 작은 말, 먼저 넣은 말)이 앞이다.
     *
     * 반환 리스트 order 의 값은 점수가 아니라 참가 인덱스이다.
     * 처음에는 0, 1, 2... 참가 순서 그대로이다.
     * 이중 반복이 자리를 바꾼 뒤, order.get(0) 이 1위 참가의 인덱스이다.
     *
     * 비교는 Integer 객체끼리 == 로 하지 않는다.
     * scores 에서 int 로 꺼낸 leftScore, rightScore 로 비교한다.
     * 128 이상이면 값이 같아도 Integer 객체가 달라 == 가 실패할 수 있다.
     *
     * 자리 바꾸기 조건
     * higher          : 뒤쪽 참가의 점수가 더 크다. 그 참가를 앞으로 보낸다.
     * sameButEarlier  : 점수는 같은데, 뒤쪽에 있는 참가 인덱스가 더 작다.
     *                   더 작다는 것은 더 먼저 참가했다는 뜻이라 앞으로 보낸다.
     *
     * 예) 2라운드 번개 28, 막차 28.
     * 인덱스는 번개 0, 막차 1. 점수가 같고 0이 이미 앞이므로 자리를 바꾸지 않는다.
     * 먼저 참가한 번개가 1위로 남는다.
     *
     * 이 기본 경로는 if 로 자리를 바꾼다. List.sort 와 람다는 쓰지 않는다.
     */
    private ArrayList<Integer> rank(ArrayList<Integer> scores) {
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            order.add(i);
        }
        for (int i = 0; i < order.size(); i++) {
            for (int j = i + 1; j < order.size(); j++) {
                int left = order.get(i);
                int right = order.get(j);
                int leftScore = scores.get(left);
                int rightScore = scores.get(right);
                boolean higher = rightScore > leftScore;
                boolean sameButEarlier = rightScore == leftScore && right < left;
                if (higher || sameButEarlier) {
                    order.set(i, right);
                    order.set(j, left);
                }
            }
        }
        return order;
    }

    /*
     * 기록이 비어 있으면 "아직 기록이 없습니다."
     * 기록이 있으면 첫 줄 "📒 기록" 다음에 저장해 둔 제목과 순위 줄을 순서대로 찍는다.
     * "📒 기록" 자체는 저장하지 않는다. 경주 안에 있는 줄만 다시 출력한다.
     * for-each 로 읽고, 출력 도중에 리스트를 수정하지 않는다.
     */
    private void showRecords() {
        System.out.print("경주 번호> ");
        int raceId = scanner.nextInt();
        scanner.nextLine();
        try {
            ArrayList<String> lines = raceService.recordsOf(raceId);
            if (lines.size() == 0) {
                System.out.println("아직 기록이 없습니다.");
            } else {
                System.out.println("📒 기록");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /*
     * [다형성 체험]
     * 람다, Map, choose 로 바꾸지 않는다.
     *
     * 1. ArrayList<RaceStrategy> 를 만든다.
     * 2. FrontRunner, Closer, Marker, Pacer 를 그 순서로 담는다.
     * 3. for-each 변수의 컴파일 타입은 RaceStrategy 이다.
     *    이 자리에서 호출할 수 있는 메서드는 execute 와 label 뿐이다.
     * 4. 실행 시점의 객체는 네 클래스이다.
     *    execute 몸통은 AbstractRaceStrategy 하나이고, 그 안의 run() 이
     *    각 객체의 run 으로 가서 1라운드 점수 40, 16, 30, 24 가 된다.
     *
     * 출력
     *   🎭 전략 체험 1라운드
     *   선행 40
     *   추입 16
     *   선입 30
     *   균속 24
     */
    private void demonstrate() {
        System.out.println("🎭 전략 체험 1라운드");
        ArrayList<RaceStrategy> strategies = new ArrayList<>();
        strategies.add(new FrontRunner());
        strategies.add(new Closer());
        strategies.add(new Marker());
        strategies.add(new Pacer());
        for (RaceStrategy strategy : strategies) {
            System.out.println(strategy.label() + " " + strategy.execute(1));
        }
    }
}
