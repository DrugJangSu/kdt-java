package avengers;

public class RangedStrategy implements BattleStrategy {
    @Override
    public int damage() {
        return 25;
    }
    @Override
    public int teamScore() {
        return 25;
    }

    @Override
    public String label() {
        return "원거리";
    }
}
