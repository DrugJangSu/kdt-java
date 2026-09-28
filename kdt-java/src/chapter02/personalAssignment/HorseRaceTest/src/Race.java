import java.util.ArrayList;

public class Race {
    private final int id;                            // 경주 번호. private = 밖에서 직접 접근 불가, final = 한번 정하면 못 바꿈
    private final String title;                      // 경주 이름 (역시 못 바꿈)
    private final ArrayList<String> horseNames;      // 참가한 말 이름들
    private final ArrayList<String> jockeyNames;     // 각 말의 기수 이름들
    private final ArrayList<Integer> strategyChoices; // 각 말이 고른 전략 번호들 (리스트엔 int 대신 Integer를 씀)
    private final ArrayList<String> records;         // 경주 결과 기록 문장들


    public Race(int id, String title) {              // 경주 번호와 이름을 받아서 만든다
        this.id = id;                                // 받은 번호를 이 객체의 id에 저장 (this = 나 자신)
        this.title = title;                          // 받은 이름을 이 객체의 title에 저장
        this.horseNames = new ArrayList<>();         // 말 이름 리스트를 빈 상태로 준비
        this.jockeyNames = new ArrayList<>();        // 기수 이름 리스트를 빈 상태로 준비
        this.strategyChoices = new ArrayList<>();    // 전략 리스트를 빈 상태로 준비
        this.records = new ArrayList<>();            // 기록 리스트를 빈 상태로 준비
    }

    public void addEntry(String horseName, String jockeyName, int strategyChoice) { // 참가자 1명의 정보 3개를 받는다
        horseNames.add(horseName);                   // 말 이름을 리스트 맨 뒤에 추가
        jockeyNames.add(jockeyName);                 // 기수 이름을 리스트 맨 뒤에 추가
        strategyChoices.add(strategyChoice);         // 전략 번호를 리스트 맨 뒤에 추가 (3개가 같은 번호 자리에 들어가서 짝이 유지됨)
    }

    // ===== 참가자 정보 꺼내기 =====
    public int entryCount() {                        // 참가자가 몇 명인지 알려주는 메서드
        return horseNames.size();                    // 말 리스트의 크기 = 참가자 수
    }

    public String horseNameAt(int index) {           // index번째 참가자의 말 이름
        return horseNames.get(index);                // 리스트에서 index번째 값을 꺼내 돌려줌 (0부터 시작)
    }

    public String jockeyNameAt(int index) {          // index번째 참가자의 기수 이름
        return jockeyNames.get(index);               // 기수 리스트에서 index번째를 꺼냄
    }

    public int strategyChoiceAt(int index) {         // index번째 참가자의 전략 번호
        return strategyChoices.get(index);           // 전략 리스트에서 index번째를 꺼냄 (Integer가 int로 자동 변환됨)
    }

    // ===== 기록 관리 =====
    public void clearRecords() {                     // 기록을 전부 지우는 메서드
        records.clear();                             // 리스트를 빈 상태로 만든다
    }

    public void addRecord(String line) {             // 기록 한 줄을 추가하는 메서드
        records.add(line);                           // 기록 리스트 맨 뒤에 추가
    }

    public ArrayList<String> getRecords() {          // 기록 목록을 밖에 알려주는 메서드
        return new ArrayList<>(records);             // 원본이 아니라 복사본을 준다 → 밖에서 지워도 원본은 안전 (방어적 복사)
    }

    // ===== 번호, 이름 꺼내기 =====
    public int getId() {                             // 경주 번호를 돌려주는 메서드 (getter)
        return id;                                   // 저장된 id를 그대로 반환
    }

    public String getTitle() {                       // 경주 이름을 돌려주는 메서드 (getter)
        return title;                                // 저장된 title을 그대로 반환
    }
}
