package racing;

/*
 * [역할] 커스텀 예외. IllegalArgumentException 의 자식이다.
 *
 * [이 예외를 던지는 곳 두 가지]
 * 과제에서 커스텀 예외는 세 개만 만든다.
 * 그래서 "말 없음"과 "경주 없음"이 이 클래스를 같이 쓴다.
 * 구분은 클래스 이름이 아니라 메시지 문장이다.
 *
 *   말 없음  HorseRepository.findById
 *            "말을 찾을 수 없습니다. 번호=" + 번호
 *   경주 없음 RaceRepository.findById
 *            "경주를 찾을 수 없습니다. 번호=" + 번호
 *
 * 일반적인 코드라면 경주 없음은 RaceNotFoundException 처럼 대상을 드러내는 편이 낫다.
 * 이번 과제는 예외 개수를 세 개로 제한한 설계라 메시지 문장으로만 나눈다.
 *
 * [왜 IllegalArgumentException 을 상속하는가]
 * 컨트롤러는 catch (IllegalArgumentException exception) 한 칸으로
 * 빈 이름, 말 없음, 기수 없음, 경주 없음, 전략 번호 오류를 모두 받는다.
 * 자식 예외도 부모 타입으로 잡히고, 화면에 나가는 문장은 getMessage() 이다.
 * Exception 을 그대로 상속하면 호출하는 메서드마다 throws 를 적어야 한다.
 * 이 과제의 실패는 잘못된 번호와 빈 이름이라 그 계열로 둔다.
 *
 * [빈 이름은 이 클래스가 아니다]
 * 말 이름이 "" 이면 HorseService 가 IllegalArgumentException 을 직접 던진다.
 * 문장은 "말 이름이 비어 있습니다." 이고, 그때는 번호가 증가하면 안 된다.
 *
 * [null 을 돌려주지 않는 이유]
 * findById 는 없으면 이 예외를 던진다. null 을 반환하지 않는다.
 * 호출하는 쪽이 null 검사를 깜빡해도, 없는 번호는 여기서 바로 멈춘다.
 */
public class HorseNotFoundException extends IllegalArgumentException {

    /*
     * 메시지 하나만 받아 부모에게 넘긴다.
     * 부모 생성자에 들어가야 getMessage() 로 같은 문장이 나온다.
     */
    public HorseNotFoundException(String message) {
        super(message);
    }
}
