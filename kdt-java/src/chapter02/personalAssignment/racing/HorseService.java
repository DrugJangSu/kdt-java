package racing;

/*
 * [역할] 말 등록과 말 조회만 담당하는 서비스이다.
 *
 * [저장소를 하나만 아는 이유]
 * 이 클래스는 HorseRepository 만 필드로 가진다.
 * 기수, 경주, 전략은 여기서 찾지 않는다.
 * 한 서비스의 생성자가 저장소를 두 개 받으면, 참가 등록처럼 여러 대상을 잇는 일이
 * 서비스 안으로 섞인다. 그 연결은 컨트롤러가 서비스 호출 순서로 만든다.
 *
 * [생성자 주입]
 * 이 클래스는 new HorseRepository() 를 하지 않는다.
 * Main 이 만든 저장소를 생성자 인자로 받는다.
 * 밖에서 넘긴 그 맵이 프로그램이 끝날 때까지 말 명부이다.
 * 서비스 안에서 저장소를 새로 만들면 Main 의 저장소와 별개의 빈 맵이 생긴다.
 *
 * [등록 흐름]
 * 컨트롤러가 이름을 읽어 register(name) 을 호출한다.
 * 이름이 "" 이면 저장소의 save 를 호출하지 않고 예외를 던진다.
 * 그래서 거절된 이름은 번호가 증가하지 않는다.
 * 통과하면 repository.save 가 번호를 발급하고 Horse 를 돌려준다.
 *
 * [빈 이름은 커스텀 예외가 아니다]
 * HorseNotFoundException 이 아니라 IllegalArgumentException 이다.
 * 문장은 "말 이름이 비어 있습니다." 이다. "비어"와 "있습니다" 사이에 공백이 있다.
 * 컨트롤러는 catch (IllegalArgumentException) 한 칸으로 이 문장을 출력한다.
 *
 * [조회]
 * findById 는 저장소에 그대로 넘긴다.
 * 없으면 저장소가 HorseNotFoundException 을 던지고, 이 서비스는 null 을 만들지 않는다.
 */
public class HorseService {
    private final HorseRepository repository;

    /* Main 이 만든 말 저장소 하나만 받는다. */
    public HorseService(HorseRepository repository) {
        this.repository = repository;
    }

    /*
     * 빈 문자열만 거절한다. 공백만 있는 문자열은 이번 과제에서 검사하지 않는다.
     * equals("") 로 비교한다. 통과한 이름만 save 로 내려간다.
     */
    public Horse register(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("말 이름이 비어 있습니다.");
        }
        return repository.save(name);
    }

    /* 번호로 말을 찾는다. 예외 메시지 조립은 HorseRepository 가 한다. */
    public Horse findById(int id) {
        return repository.findById(id);
    }
}
