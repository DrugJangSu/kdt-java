package racing;

/*
 * [역할] 커스텀 예외. 기수 번호로 찾았는데 저장소에 없을 때 던진다.
 *
 * [메시지]
 * JockeyRepository.findById 가 아래 문장으로 만든다.
 *   "기수를 찾을 수 없습니다. 번호=" + 번호
 * 번호 뒤에 등호가 붙고, 등호와 번호 사이에 공백은 없다.
 * 예) 기수를 찾을 수 없습니다. 번호=9
 *
 * [HorseNotFoundException 과 클래스를 나눈 이유]
 * 말 없음과 기수 없음은 둘 다 "없는 번호"이지만 대상이 다르다.
 * 기수 조회 실패를 말 예외로 던지면, 화면 문장과 예외 타입이 어긋난다.
 * 경주 없음만 예외 개수 제한 때문에 HorseNotFoundException 을 재사용한다.
 * 기수는 자기 예외가 있다.
 *
 * [컨트롤러에서 잡히는 방식]
 * extends IllegalArgumentException 이므로
 * 참가 등록의 catch (IllegalArgumentException) 한 칸에 들어간다.
 * 검사 순서는 경주 → 말 → 기수 → 전략이다.
 * 기수 번호가 없어서 이 예외가 나면, 그 뒤의 전략 선택과 enter 는 호출하지 않는다.
 * 참가 목록은 늘어나지 않는다.
 *
 * [빈 이름은 이 클래스가 아니다]
 * 기수 이름이 "" 이면 JockeyService 가 IllegalArgumentException("기수 이름이 비어 있습니다.") 를 던진다.
 */
public class JockeyNotFoundException extends IllegalArgumentException {

    /* 받은 문장을 부모 IllegalArgumentException 에 전달한다. */
    public JockeyNotFoundException(String message) {
        super(message);
    }
}
