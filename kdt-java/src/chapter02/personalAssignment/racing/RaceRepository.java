package racing;

import java.util.HashMap;

/*
 * [역할] 경주 저장소. 메모리에 경주 객체를 번호로 보관한다. DB 는 쓰지 않는다.
 *
 * [HashMap 에 넣는 것]
 * 키는 경주 번호, 값은 Race 객체 하나이다.
 * 참가 말 이름, 기수 이름, 전략 번호, 기록 문장은 Race 안의 ArrayList 가 가진다.
 * 이 저장소는 "몇 번 경주인가"만 찾고, 참가 순서를 정렬하지 않는다.
 *
 * [save 와 참가 추가의 차이]
 * save(제목) 은 새 경주를 만들고 새 번호를 발급한다. 메뉴 3 "경주 만들기".
 * 참가 등록은 새 번호를 만들지 않는다.
 * findById 로 이미 있는 Race 를 꺼내 그 객체의 addEntry 를 호출한다.
 * 맵에 들어 있는 같은 객체의 리스트만 길어진다.
 *
 * [번호]
 * nextNumber 는 1부터다. 말 번호, 기수 번호와 따로 센다.
 * 첫 경주 "서울 1경주"는 번호 1이다.
 * 빈 제목("") 은 RaceService.create 가 save 전에 거절한다.
 * 문장은 "경주 이름이 비어 있습니다." 이고, 거절되면 번호는 올라가지 않는다.
 *
 * [없는 경주의 예외 타입이 HorseNotFoundException 인 이유]
 * 커스텀 예외는 HorseNotFoundException, JockeyNotFoundException, InvalidStrategyException 세 개뿐이다.
 * 네 번째 예외를 만들지 말라는 과제 규칙이라, 경주 없음도 첫 번째 예외 클래스를 쓴다.
 * 말 없음과 클래스 이름은 같고, 메시지만 다르다.
 *   "경주를 찾을 수 없습니다. 번호=" + 번호
 * 컨트롤러는 부모 타입 IllegalArgumentException 으로 이 문장을 출력한다.
 *
 * [findById 는 null 을 반환하지 않는다]
 * 맵에 없으면 예외이다. 참가 등록, 경주 시작, 기록 보기가 모두 이 조회로 시작한다.
 */
public class RaceRepository {
    private final HashMap<Integer, Race> store = new HashMap<>();
    private int nextNumber = 1;

    /*
     * 이번 경주 번호를 꺼내고 다음 번호를 1 올린다.
     * 참가 추가(addEntry) 때는 호출하지 않는다. 새 경주를 만들 때만 쓴다.
     */
    private int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    /*
     * 빈 참가 명단, 빈 기록을 가진 Race 를 만들어 맵에 넣는다.
     * 반환한 객체의 getId(), getTitle() 로
     * "🏁 경주를 만들었습니다. 번호 1, 서울 1경주" 를 조립한다.
     */
    public Race save(String title) {
        int id = nextId();
        Race race = new Race(id, title);
        store.put(id, race);
        return race;
    }

    /*
     * 번호로 경주를 찾는다.
     * 없으면 HorseNotFoundException 이지만 문장은 "경주를"로 시작한다.
     * 이 예외가 나면 그 요청에서는 말 조회, 기수 조회, 전략 선택, 기록 삭제로 내려가지 않는다.
     */
    public Race findById(int id) {
        Race race = store.get(id);
        if (race == null) {
            throw new HorseNotFoundException("경주를 찾을 수 없습니다. 번호=" + id);
        }
        return race;
    }
}
