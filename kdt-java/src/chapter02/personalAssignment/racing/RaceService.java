package racing;

import java.util.ArrayList;

/*
 * [역할] 경주를 만들고, 찾고, 참가를 넣고, 기록을 지우고 더하고 읽는 서비스이다.
 *
 * [이 서비스가 알지 못하는 것]
 * 생성자는 RaceRepository 하나만 받는다.
 * HorseRepository, JockeyRepository 는 필드에 없다.
 * enter 가 받는 것은 이미 확인이 끝난 말 이름, 기수 이름, 전략 번호이다.
 * 말과 기수가 실재하는지는 컨트롤러가 HorseService, JockeyService 로 먼저 확인한다.
 * 그래서 이 클래스는 "경주 저장과 그 경주 내용 변경"에만 집중한다.
 *
 * [만들기와 참가 추가의 차이]
 * create 는 새 경주 번호를 발급한다. 메뉴 3.
 * enter 는 이미 있는 경주를 찾아 addEntry 만 호출한다. 메뉴 4.
 * 참가를 넣어도 경주 번호는 늘어나지 않는다. 맵 안의 같은 Race 객체를 고친다.
 *
 * [기록 메서드가 나뉜 이유]
 * clearRecords, addRecord, recordsOf 는 경주 시작과 기록 보기가 호출하는 입구이다.
 * 참가 수가 2마리 미만인데 기록을 지울지는 이 서비스가 판단하지 않는다.
 * 그 순서는 컨트롤러가 지킨다. 확인이 끝난 뒤에만 clearRecords 를 호출한다.
 *
 * [recordsOf 가 돌려주는 리스트]
 * Race.getRecords 가 원본이 아니라 복사본을 준다.
 * 메뉴 6이 그 리스트를 읽기만 해도, 호출자가 clear 해도 경주 원본 기록은 남는다.
 */
public class RaceService {
    private final RaceRepository repository;

    /* Main 이 만든 경주 저장소 하나만 받는다. */
    public RaceService(RaceRepository repository) {
        this.repository = repository;
    }

    /*
     * 제목이 "" 이면 저장하지 않는다.
     * 예외는 IllegalArgumentException 이고 문장은 "경주 이름이 비어 있습니다."
     * 통과하면 저장소가 번호를 붙여 Race 를 반환한다.
     */
    public Race create(String title) {
        if (title.equals("")) {
            throw new IllegalArgumentException("경주 이름이 비어 있습니다.");
        }
        return repository.save(title);
    }

    /*
     * 없으면 RaceRepository 가 HorseNotFoundException 을 던진다.
     * 클래스 이름은 말 예외와 같고, 메시지는 "경주를 찾을 수 없습니다. 번호=" 이다.
     */
    public Race findById(int id) {
        return repository.findById(id);
    }

    /*
     * 경주를 다시 찾은 뒤, 그 객체의 참가 리스트 세 칸에 같은 인덱스로 넣는다.
     * 새 경주를 만들지 않는다.
     * 전략 번호가 1~4 인지는 여기 오기 전에 StrategyService.choose 가 검사한다.
     * choose 가 실패하면 컨트롤러가 이 메서드를 호출하지 않는다.
     */
    public void enter(int raceId, String horseName, String jockeyName, int strategyChoice) {
        Race race = repository.findById(raceId);
        race.addEntry(horseName, jockeyName, strategyChoice);
    }

    /*
     * 이 경주의 기록 문장만 비운다. 참가 명단은 그대로이다.
     * 호출 시점: 참가가 2마리 이상인 것을 컨트롤러가 확인한 뒤, 새 라운드 계산 전.
     */
    public void clearRecords(int raceId) {
        repository.findById(raceId).clearRecords();
    }

    /*
     * 화면에 찍은 문장과 같은 문자열을 한 줄 저장한다.
     * 라운드 제목, 순위 줄, 최종 제목, 최종 순위 줄이 모두 이 경로로 쌓인다.
     */
    public void addRecord(int raceId, String line) {
        repository.findById(raceId).addRecord(line);
    }

    /* 메뉴 6이 다시 찍을 기록 복사본을 돌려준다. 경주가 없으면 findById 에서 예외가 난다. */
    public ArrayList<String> recordsOf(int raceId) {
        return repository.findById(raceId).getRecords();
    }
}
