package racing;

import java.util.ArrayList;

/*
 * [역할] 도메인. 경주 한 건이다. 예) 번호 1, 제목 "서울 1경주"
 *
 * [기억하는 것 두 덩어리]
 * 1. 참가 명단: 누가, 어느 기수로, 어떤 전략 번호로 들어왔는가. 넣은 순서를 유지한다.
 * 2. 기록 문장: 경주를 돌린 뒤 화면에 찍은 줄과 똑같은 문자열. 메뉴 6에서 다시 찍는다.
 *
 * [왜 참가 정보를 HashMap 이 아니라 ArrayList 세 개로 두는가]
 * HashMap 은 "번호로 한 건 찾기"에 강하다. 말 저장소, 기수 저장소, 경주 저장소가 그 역할이다.
 * 참가 순위는 "먼저 등록한 말이 동점일 때 앞"이라는 규칙이 있다.
 * HashMap 을 반복하는 순서는 넣은 순서와 같다고 보장되지 않는다.
 * ArrayList 는 add 한 순서가 인덱스 0, 1, 2... 로 남으므로 참가 순서를 믿을 수 있다.
 *
 * [왜 리스트가 세 개인가]  병렬 리스트
 * 참가 한 건은 (말 이름, 기수 이름, 전략 번호) 세 값이다.
 * addEntry 가 세 리스트의 맨 끝에 동시에 넣으므로, 같은 인덱스가 같은 참가이다.
 *
 *   index | horseNames | jockeyNames | strategyChoices
 *   ------+------------+-------------+----------------
 *   0     | 번개       | 강기수      | 1  (선행)
 *   1     | 막차       | 한기수      | 2  (추입)
 *
 * 경주 시작 때 i번 참가의 점수는 strategyChoiceAt(i) 로 전략을 고르고,
 * 화면 줄은 horseNameAt(i), jockeyNameAt(i) 로 조립한다.
 * Horse 객체와 Jockey 객체를 통째로 담지 않는다.
 * 컨트롤러가 이미 조회를 끝낸 뒤 이름과 전략 번호만 넘기는 구조이기 때문이다.
 * 그래서 Race 는 말 저장소, 기수 저장소를 몰라도 된다.
 *
 * [번호는 참가를 추가해도 바뀌지 않는다]
 * addEntry 는 이 객체의 리스트만 늘린다. 새 경주 번호를 만들지 않는다.
 * 저장소 HashMap 안의 같은 Race 객체를 고치는 것이다.
 *
 * [기록 흐름]  메뉴 5 → 메뉴 6
 * 1. 참가 수 entryCount() 가 2 미만이면 경주를 시작하지 않는다.
 *    이때 clearRecords 를 호출하면 안 된다. 이전의 정상 기록이 사라지기 때문이다.
 * 2. 2마리 이상이면 clearRecords 로 예전 기록을 비운 다음, 라운드 제목과 순위 줄을 addRecord 한다.
 * 3. 메뉴 6은 getRecords 로 그 줄들을 받아 같은 순서로 다시 찍는다.
 * 4. 같은 경주를 다시 시작하면 1번 조건 확인 후 기록을 지우고 새 결과만 남긴다.
 *
 * [toString 을 두지 않는 이유]
 * "1위 번개 / 강기수 / 선행 / 40" 같은 문장은 컨트롤러가 getter 를 이어 붙인다.
 * 예) race.horseNameAt(index) + " / " + race.jockeyNameAt(index)
 */
public class Race {
    private final int id;
    private final String title;

    /*
     * 세 리스트의 final 은 "리스트 변수 자체"를 다른 리스트로 갈아끼우지 않는다는 뜻이다.
     * 리스트 안에 add 로 참가를 넣는 것은 허용된다. final 이 내용 변경까지 막지는 않는다.
     * 생성자에서 빈 리스트를 만들어 두어야 addEntry 전에 null 이 되지 않는다.
     */
    private final ArrayList<String> horseNames;
    private final ArrayList<String> jockeyNames;
    private final ArrayList<Integer> strategyChoices;
    private final ArrayList<String> records;

    public Race(int id, String title) {
        this.id = id;
        this.title = title;
        this.horseNames = new ArrayList<>();
        this.jockeyNames = new ArrayList<>();
        this.strategyChoices = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    /*
     * 참가 한 건을 세 리스트의 같은 위치(맨 끝)에 넣는다.
     * 넣는 순서가 곧 참가 순서이다. 나중에 동점이면 인덱스가 작은 말이 앞이다.
     * 전략 번호 1~4 검사, 말/기수 존재 검사는 여기 오기 전에 컨트롤러와 서비스가 끝낸다.
     * 이 메서드가 호출됐다는 것은 이미 통과한 참가라는 뜻이다.
     */
    public void addEntry(String horseName, String jockeyName, int strategyChoice) {
        horseNames.add(horseName);
        jockeyNames.add(jockeyName);
        strategyChoices.add(strategyChoice);
    }

    /*
     * 참가 마리 수. 세 리스트는 항상 같이 늘어나므로 horseNames 길이만 보면 된다.
     * 경주 시작 전에 이 값이 2 미만이면 "참가 말이 2마리 미만입니다." 이고 기록은 지우지 않는다.
     */
    public int entryCount() {
        return horseNames.size();
    }

    /* index 는 0부터. 0이 첫 번째로 참가한 말이다. */
    public String horseNameAt(int index) {
        return horseNames.get(index);
    }

    public String jockeyNameAt(int index) {
        return jockeyNames.get(index);
    }

    /*
     * 리스트에 들어 있는 타입은 Integer 이다. 반환 타입은 int 이다.
     * get 이 돌려준 Integer 가 int 로 자동 변환(unboxing)된다.
     * 순위 계산을 할 때는 이렇게 꺼낸 int 값으로 비교한다.
     * Integer 객체끼리 == 로 비교하면 128 이상에서 값이 같아도 다른 객체로 보일 수 있다.
     */
    public int strategyChoiceAt(int index) {
        return strategyChoices.get(index);
    }

    /*
     * 이 경주의 기록만 비운다. 참가 명단(말, 기수, 전략)은 그대로 둔다.
     * 호출 시점: 참가가 2마리 이상인 것을 확인한 뒤, 새 라운드를 계산하기 전.
     */
    public void clearRecords() {
        records.clear();
    }

    /*
     * 화면에 찍은 문장과 같은 문자열을 한 줄 추가한다.
     * 라운드 제목, "1위 ..." 줄, "🏆 최종 순위", 최종 순위 줄이 모두 이 경로로 쌓인다.
     */
    public void addRecord(String line) {
        records.add(line);
    }

    /*
     * 내부 records 를 그대로 돌려주지 않고 새 ArrayList 로 복사해서 돌려준다.
     *
     * 그대로 돌려주면 메뉴 6을 처리하는 쪽이 lines.clear() 를 호출하는 순간
     * Race 가 보관 중인 원본 기록까지 지워진다.
     * 복사본을 주면 호출자가 그 리스트를 고쳐도 경주 기록은 그대로이다.
     *
     * new ArrayList<>(records) 는 records 안의 문자열을 순서대로 담은 새 리스트이다.
     * 문자열 내용이 아니라 "리스트 상자"가 분리되는 것이다.
     */
    public ArrayList<String> getRecords() {
        return new ArrayList<>(records);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
