package racing;

/*
 * [역할] 도메인. 서울경마공원이 기억하는 "기수 한 명"이다.
 *
 * [이 클래스가 기억하는 것]
 * - id   : 등록 순서대로 붙는 번호. 말 번호와는 따로 센다.
 *         말을 두 마리 등록해도 첫 기수는 1번이다.
 * - name : 기수 이름. 예) 강기수, 한기수
 *
 * [Horse 와 모양이 같은데 클래스를 나눈 이유]
 * 번호와 이름만 있다고 해서 같은 타입으로 두면,
 * 말 번호 자리에 기수를 넣어도 컴파일러가 막지 못한다.
 * "말은 말, 기수는 기수"로 타입을 나누면 서비스와 저장소도 같이 갈라진다.
 * HorseService 는 말 저장소만, JockeyService 는 기수 저장소만 안다.
 *
 * [상속으로 묶지 않는 이유]
 * 공통 부모(예: NamedEntry)를 만들면 코드는 짧아지지만,
 * 이 과제에서 말과 기수는 서로 대체되는 관계가 아니다.
 * 기수는 말이 아니고, 말은 기수가 아니다.
 *
 * [전체 흐름에서 어디에 쓰이나]
 * 1. JockeyRepository.save 가 new Jockey(번호, 이름) 으로 만든다.
 * 2. 참가 등록(메뉴 4)에서 컨트롤러가 jockey.getName() 만 꺼내 경주에 넘긴다.
 * 3. 화면 문장은 getter 로 조립한다.
 *    예) "👤 기수를 등록했습니다. 번호 " + jockey.getId() + ", " + jockey.getName()
 *
 * [설계]
 * - private final + getter 만 둔다. setter 와 toString 은 없다.
 * - 빈 이름("") 검사는 이 생성자가 아니라 JockeyService.register 가 한다.
 *   실패 문장은 "기수 이름이 비어 있습니다." 이고, 이때는 번호가 증가하면 안 된다.
 */
public class Jockey {
    private final int id;
    private final String name;

    public Jockey(int id, String name) {
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
