package avengers;

public class MeleeStrategy implements BattleStrategy {
    @Override
    public int damage() {
        return 40;
    }
    @Override
    public int teamScore() {
        return 15;
    }

    @Override
    public String label() {
        return "근접";
    }
}