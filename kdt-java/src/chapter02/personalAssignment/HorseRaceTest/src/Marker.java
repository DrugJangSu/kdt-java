public class Marker extends AbstractRaceStrategy {
    public Marker() {
        super("선입");
    }

    // AbstractRaceStrategy.run을 구현한다. 선입은 세 라운드가 같다.
    @Override
    protected int run(int round) {
        if (round == 1) {
            return 30;
        } else if (round == 2) {
            return 30;
        } else if (round == 3) {
            return 30;
        } else {
            return 0;
        }
    }
}