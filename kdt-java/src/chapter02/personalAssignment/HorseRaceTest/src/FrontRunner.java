public class FrontRunner extends racing.AbstractRaceStrategy {
    public FrontRunner() {
        super("선행");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 40;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 16;
        } else {
            return 0;
        }
    }
}
