package avengers;

public class MissionLog {
    private final int id;
    private final String missionTitle;
    private final String heroName;
    private final String strategyLabel;
    private final int damage;
    private final int teamScore;

    public MissionLog(int id, String missionTitle, String heroName, String strategyLabel, int damage, int teamScore) {
        this.id = id;
        this.missionTitle = missionTitle;
        this.heroName = heroName;
        this.strategyLabel = strategyLabel;
        this.damage = damage;
        this.teamScore = teamScore;
    }

    public int getId() {
        return id;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getStrategyLabel() {
        return strategyLabel;
    }

    public int getDamage() {
        return damage;
    }

    public int getTeamScore() {
        return teamScore;
    }
}
