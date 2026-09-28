package racing;

import java.util.HashMap;

/*
 * [역할] 말 저장소. 메모리 안의 말 명부이다. DB 는 쓰지 않는다.
 *
 * [HashMap 을 쓰는 이유]
 * 이 저장소의 일은 "번호로 말 한 마리를 찾기"이다.
 * 키는 말 번호(Integer), 값은 Horse 객체이다.
 * 전체 말을 순서대로 찍는 메뉴는 없다. 그래서 순서용 ArrayList 는 두지 않는다.
 * 참가 순서와 동점 순위는 이 맵의 반복 순서로 판단하면 안 된다.
 * HashMap 을 꺼내는 순서는 등록 순서와 같다고 보장되지 않는다.
 * 그 순서는 Race 안의 ArrayList 가 기억한다.
 *
 * [번호는 1부터 1씩 증가]
 * nextNumber 가 다음에 줄 번호이다. 처음 값은 1.
 * save 가 성공할 때마다 nextId() 가 현재 번호를 돌려주고 nextNumber 를 1 올린다.
 * 첫 저장은 1번(번개), 두 번째 저장은 2번(막차)이다.
 *
 * [빈 이름 검사는 여기서 하지 않는다]
 * 이름이 "" 이면 HorseService.register 가 save 를 호출하기 전에 예외를 던진다.
 * 그래서 거절된 이름은 이 맵에 들어가지 않고, nextNumber 도 올라가지 않는다.
 * 저장소는 "이미 통과한 이름을 번호와 함께 넣기"만 한다.
 *
 * [findById 는 null 을 돌려주지 않는다]
 * 맵에 그 번호가 없으면 HorseNotFoundException 을 던진다.
 * 메시지: "말을 찾을 수 없습니다. 번호=" + 번호
 *
 * [누가 이 객체를 만드나]
 * 나중에 Main 이 new HorseRepository() 를 하고, HorseService 생성자에 넘긴다.
 * 서비스가 저장소를 스스로 new 하지 않는다. 생성자 주입이다.
 * 서비스 하나의 생성자는 저장소를 하나만 받는다.
 */
public class HorseRepository {
    private final HashMap<Integer, Horse> store = new HashMap<>();
    private int nextNumber = 1;

    /*
     * 이번에 쓸 번호를 꺼내고, 다음 번호로 한 칸 민다.
     * private 이라 저장소 밖에서 번호를 건너뛰거나 되돌리지 못한다.
     * 호출 순서: save 가 이름을 받은 뒤, Horse 를 만들기 직전에 한 번.
     */
    private int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    /*
     * 말을 만들어 맵에 넣고, 그 객체를 그대로 돌려준다.
     * 반환값을 주는 이유: 서비스와 컨트롤러가 getId(), getName() 으로
     * "🐴 말을 등록했습니다. 번호 1, 번개" 를 조립해야 하기 때문이다.
     * 화면 문장은 이 메서드가 찍지 않는다.
     */
    public Horse save(String name) {
        int id = nextId();
        Horse horse = new Horse(id, name);
        store.put(id, horse);
        return horse;
    }

    /*
     * 번호로 말을 찾는다.
     * store.get 은 없을 때 null 을 준다. 그 null 을 밖으로 넘기지 않고 예외로 바꾼다.
     * 참가 등록은 이 예외가 나면 기수 조회와 전략 선택으로 내려가지 않는다.
     */
    public Horse findById(int id) {
        Horse horse = store.get(id);
        if (horse == null) {
            throw new HorseNotFoundException("말을 찾을 수 없습니다. 번호=" + id);
        }
        return horse;
    }
}
