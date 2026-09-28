package racing;

/*
 * [역할] 도메인. 서울경마공원이 기억하는 "말 한 마리"이다.
 *
 * [이 클래스가 기억하는 것]
 * - id   : 등록 순서대로 붙는 번호. 화면의 "번호 1, 번개"에서 1이 이 값이다.
 * - name : 말 이름. 예) 번개, 막차
 *
 * [이 클래스가 일부러 기억하지 않는 것]
 * 기수, 주행 전략, 라운드 점수, 상금은 말의 속성이 아니다.
 * 같은 말이라도 경주마다 다른 기수와 다른 전략으로 나갈 수 있다.
 * 그래서 전략 번호는 나중에 Race가 참가 등록 시점에 따로 기억한다.
 *
 * [전체 흐름에서 어디에 쓰이나]
 * 1. HorseRepository.save 가 new Horse(번호, 이름) 으로 만든다.
 * 2. HorseService.register / findById 가 이 객체를 돌려준다.
 * 3. 참가 등록(메뉴 4)에서 컨트롤러가 horse.getName() 만 꺼내
 *    RaceService.enter(...) 로 이름 문자열을 넘긴다.
 *    경주는 Horse 객체 자체가 아니라 이름 사본을 보관한다.
 *
 * [설계]
 * - 필드는 private: 클래스 밖에서 id, name 을 직접 읽거나 바꾸지 못한다.
 * - 필드는 final: 생성자에서 한 번 정하면 그 뒤로는 바뀌지 않는다. setter 가 없는 이유이다.
 * - 값은 getter 로만 꺼낸다. 화면 문장은 이 클래스가 찍지 않고, 호출하는 쪽이 이어 붙인다.
 *   예) "🐴 말을 등록했습니다. 번호 " + horse.getId() + ", " + horse.getName()
 * - toString 은 만들지 않는다. 로그용 문장과 화면 문장이 한 메서드에 묶이면
 *   화면 형식을 바꿀 때 다른 출력까지 같이 바뀌기 때문이다.
 * - Jockey 와 필드 모양이 같아도 상속으로 묶지 않는다. 기수는 말의 한 종류가 아니다.
 */
public class Horse {
    private final int id;
    private final String name;

    /*
     * 저장소가 번호를 만들어 넘긴다. 이 생성자는 검사하지 않는다.
     * 빈 이름("") 거부는 HorseService.register 의 책임이다.
     * 여기서 막으면 번호 발급 위치와 검증 위치가 두 군데가 된다.
     */
    public Horse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
