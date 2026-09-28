public class Closer extends AbstractRaceStrategy {
    public Closer() {
        super("추입");
    }

    @Override
    protected int run(int round) {
        if (round == 1) {
            return 16;
        } else if (round == 2) {
            return 28;
        } else if (round == 3) {
            return 42;
        } else {
            return 0;
        }
    }
}
