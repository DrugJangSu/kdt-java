public abstract class AbstractRaceStrategy implements RaceStrategy { // abstract = 혼자서는 new 불가, 상속받는 클래스가 나머지를 채워야 함. implements = RaceStrategy 규칙을 따르겠다
    private final String label;                     // 전략 이름표를 저장하는 필드 (밖에서 접근 불가, 한번 정하면 못 바꿈)

    protected AbstractRaceStrategy(String label) {  // 생성자. protected = 자식 클래스에서만 호출 가능
        this.label = label;                         // 받은 이름표를 내 label에 저장 (this = 나 자신)
    }

    @Override                                       // "인터페이스의 메서드를 구현했다"는 표시 (오타 방지용)
    public final int execute(int round) {           // final = 자식이 이 메서드를 못 바꿈. 실행 순서를 고정하는 핵심 메서드
        prepare();                                  // 1단계: 준비 (기본은 아무것도 안 함)
        int pace = run(round);                      // 2단계: 실제 달리기 (자식이 반드시 직접 만들어야 함)
        return finish(pace);                        // 3단계: 마무리 처리 후 결과 반환 (기본은 그대로 반환)
    }

    protected void prepare() {                      // 준비 단계. 내용이 비어 있음 → 자식이 필요하면 덮어쓰기(override)
    }

    protected abstract int run (int round);         // abstract = 몸통 없음. 자식이 "어떻게 달릴지"를 반드시 채워야 함

    protected int finish (int pace) {               // 마무리 단계. 기본 동작은 받은 값을 그대로 돌려줌
        return pace;                                // 자식이 필요하면 덮어써서 보정 가능 (예: 최대값 제한)
    }

    @Override                                       // 인터페이스의 label()을 구현
    public String label() {                         // 전략 이름표를 꺼내는 메서드
        return label;                               // 생성자에서 저장해 둔 이름표를 반환
    }
}