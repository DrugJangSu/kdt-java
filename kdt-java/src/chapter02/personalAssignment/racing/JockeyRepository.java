package racing;

import java.util.HashMap;

/*
 * [역할] 기수 저장소. 메모리 안의 기수 명부이다. DB 는 쓰지 않는다.
 *
 * [말 저장소와 맵을 나누는 이유]
 * 번호 1은 말에도 있고 기수에도 있다.
 * 말 "번개"가 1번이어도 기수 "강기수"의 1번과는 다른 칸이다.
 * 맵을 하나에서 공유하면 같은 번호가 서로 덮어쓴다.
 * HorseRepository 는 Horse 만, 이 클래스는 Jockey 만 담는다.
 *
 * [HashMap]
 * 키는 기수 번호, 값은 Jockey 이다.
 * 번호로 한 명을 찾는 용도이고, 전체 목록 메뉴는 없다.
 * 경주에 나간 순서는 여기 반복 순서가 아니라 Race 의 ArrayList 인덱스이다.
 *
 * [번호]
 * nextNumber 는 1부터 시작한다. 말 번호와 따로 센다.
 * 말을 두 마리 등록한 뒤 기수를 처음 등록해도 기수 번호는 1이다.
 *
 * [빈 이름]
 * "기수 이름이 비어 있습니다." 검사는 JockeyService.register 가 한다.
 * 이 save 는 검사를 통과한 이름만 받는다. 실패 시 번호는 증가하지 않는다.
 *
 * [findById]
 * 없으면 null 대신 JockeyNotFoundException.
 * 메시지: "기수를 찾을 수 없습니다. 번호=" + 번호
 *
 * [조립]
 * Main 이 이 객체를 만들어 JockeyService 생성자에만 넘긴다.
 * RaceService 는 기수 저장소를 필드에 두지 않는다.
 * 컨트롤러가 findById 로 기수를 찾은 뒤 이름 문자열만 경주에 전달한다.
 */
public class JockeyRepository {
    private final HashMap<Integer, Jockey> store = new HashMap<>();
    private int nextNumber = 1;

    /*
     * 현재 번호를 반환하고 nextNumber 를 1 증가시킨다.
     * save 안에서만 호출한다.
     */
    private int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    /*
     * 기수 객체를 만들어 맵에 넣고 반환한다.
     * 컨트롤러는 반환 객체의 getter 로
     * "👤 기수를 등록했습니다. 번호 1, 강기수" 를 조립한다.
     */
    public Jockey save(String name) {
        int id = nextId();
        Jockey jockey = new Jockey(id, name);
        store.put(id, jockey);
        return jockey;
    }

    /*
     * 번호가 맵에 없으면 예외이다.
     * 참가 등록 검사 순서상, 경주와 말이 통과한 다음에 이 메서드가 호출된다.
     * 여기서 실패하면 전략 선택과 참가 추가는 실행되지 않는다.
     */
    public Jockey findById(int id) {
        Jockey jockey = store.get(id);
        if (jockey == null) {
            throw new JockeyNotFoundException("기수를 찾을 수 없습니다. 번호=" + id);
        }
        return jockey;
    }
}
