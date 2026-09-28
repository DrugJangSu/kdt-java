package racing;

/*
 * [역할] 기수 등록과 기수 조회만 담당하는 서비스이다.
 *
 * [HorseService 와 같은 모양인 이유]
 * 번호와 이름을 등록하고, 번호로 한 건을 찾는 흐름이 말과 같다.
 * 그래도 클래스를 합치지 않는다. 기수 저장소와 말 저장소는 번호 체계가 따로다.
 * 이 생성자는 JockeyRepository 하나만 받는다.
 *
 * [등록 흐름]
 * 이름이 "" 이면 "기수 이름이 비어 있습니다." 로 IllegalArgumentException 을 던진다.
 * save 를 호출하지 않으므로 기수 번호는 올라가지 않는다.
 * 통과하면 저장소가 1부터 번호를 붙여 Jockey 를 반환한다.
 *
 * [조회가 참가 등록에서 쓰이는 위치]
 * 컨트롤러는 경주, 말, 기수 순서로 찾는다.
 * 이 findById 가 JockeyNotFoundException 을 던지면
 * 그 뒤의 전략 선택과 RaceService.enter 는 호출되지 않는다.
 * 참가 목록은 그대로이다.
 */
public class JockeyService {
    private final JockeyRepository repository;

    /* Main 이 만든 기수 저장소 하나만 받는다. */
    public JockeyService(JockeyRepository repository) {
        this.repository = repository;
    }

    /*
     * 빈 문자열만 거절한다.
     * 이 예외는 JockeyNotFoundException 이 아니다. 없는 번호와 빈 이름은 다른 실패이다.
     */
    public Jockey register(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("기수 이름이 비어 있습니다.");
        }
        return repository.save(name);
    }

    /* 없으면 저장소가 "기수를 찾을 수 없습니다. 번호=" 예외를 던진다. */
    public Jockey findById(int id) {
        return repository.findById(id);
    }
}
