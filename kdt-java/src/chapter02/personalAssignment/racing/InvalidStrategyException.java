package racing;

/*
 * [역할] 커스텀 예외. 전략 번호가 1, 2, 3, 4 가 아닐 때 던진다.
 *
 * [메시지]
 *   "전략은 1, 2, 3, 4입니다."
 * 번호가 9여도 문장에 그 번호를 붙이지 않는다. 허용 범위만 알려 준다.
 *
 * [누가 던지나]
 * 이 클래스 자신은 문장만 보관한다.
 * 실제로 번호를 검사하는 곳은 이후 단계의 StrategyService.choose(int) 이다.
 *   1 → FrontRunner
 *   2 → Closer
 *   3 → Marker
 *   4 → Pacer
 *   그 외 → 이 예외
 * choose 는 Map 으로 전략을 고르지 않고 if ~ else if ~ else 로 고른다.
 *
 * [참가 등록에서의 위치]
 * 컨트롤러는 경주 번호, 말 번호, 기수 번호, 전략 번호를 모두 읽은 뒤에 검사한다.
 * 순서는 경주 → 말 → 기수 → choose 이다.
 * choose 가 이 예외를 던지면 RaceService.enter 까지 가지 않으므로 참가 수는 늘지 않는다.
 * 네 번호를 묻는 과정은 실패해도 이미 끝난 상태이다. 입력을 받기 전에 막지 않는다.
 *
 * [부모 타입으로 잡힌다]
 * IllegalArgumentException 의 자식이라 컨트롤러의 catch 한 칸에서 메시지만 출력하면 된다.
 * 빈 이름 예외와 클래스만 다르고, 처리 경로는 같다.
 */
public class InvalidStrategyException extends IllegalArgumentException {

    /* 받은 문장을 부모에게 넘겨 getMessage() 로 그대로 나오게 한다. */
    public InvalidStrategyException(String message) {
        super(message);
    }
}
