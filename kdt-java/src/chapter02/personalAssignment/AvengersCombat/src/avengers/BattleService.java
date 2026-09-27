package avengers;

public class BattleService {
    public BattleStrategy choose(int choice) {
        if (choice == 1) {
            return new MeleeStrategy();
        } else if (choice == 2) {
            return new RangedStrategy();
        } else if (choice == 3) {
            return new SupportStrategy();
        } else {
            throw new InvalidStrategyException("전투 방식은 1, 2, 3 입니다.");
        }
    }
}
