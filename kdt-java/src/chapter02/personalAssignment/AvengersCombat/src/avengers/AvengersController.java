package avengers;

import java.util.ArrayList;
import java.util.Scanner;

public class AvengersController {
    private final HeroService heroService;
    private final MissionService missionService;
    private final MissionLogService missionLogService;
    private final BattleService battleService;
    private final Scanner scanner;

    public AvengersController(HeroService heroService, MissionService missionService, MissionLogService missionLogService, BattleService battleService, Scanner scanner) {
        this.heroService = heroService;
        this.missionService = missionService;
        this.missionLogService = missionLogService;
        this.battleService = battleService;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        HeroRepository heroRepository = new HeroRepository();
        MissionRepository missionRepository = new MissionRepository();
        MissionLogRepository missionLogRepository = new MissionLogRepository();
        HeroService heroService = new HeroService(heroRepository);
        MissionService missionService = new MissionService(missionRepository);
        MissionLogService missionLogService = new MissionLogService(missionLogRepository);
        BattleService battleService = new BattleService();
        Scanner scanner = new Scanner(System.in);

        AvengersController controller = new AvengersController(heroService, missionService, missionLogService, battleService, scanner);
        controller.run();
    }
    public void run() {
        boolean running = true;
        while (running) {
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerHero();
            } else if (menu == 2) {
                registerMission();
            } else if (menu == 3) {
                assignHero();
            } else if (menu == 4) {
                battle();
            } else if (menu == 5) {
                printLogs();
            } else if (menu == 6) {
                printHeroes();
            } else if (menu == 7) {
                printMissions();
            } else if (menu == 0) {
                System.out.println("본부로 복귀합니다.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 7까지입니다.");
            }
        }
    }

    private void registerHero() {
        System.out.println("이름> ");
        String name = scanner.nextLine();
        try {
            Hero hero = heroService.register(name);
            System.out.println("🦸 히어로를 등록했습니다. 번호 " + hero.getId() + ", " + hero.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerMission() {
        System.out.println("미션 이름>");
        String title =  scanner.nextLine();
        try {
            Mission mission = missionService.register(title);
            System.out.println("🎯 미션을 등록했습니다. 번호 " + mission.getId() + ", " + mission.getTitle());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignHero() {
        System.out.println("히어로 번호>");
        int heroId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("미션 번호>");
        int missionId = scanner.nextInt();
        scanner.nextLine();
        try {
            Hero hero = heroService.findById(heroId);
            Mission mission = missionService.assign(missionId, hero.getName());
            System.out.println("🤝 배정했습니다. " + mission.getTitle() + " / " + mission.getHeroName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void battle() {
        System.out.println("미션 번호>");
        int missionId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("전투 방식 1.근접 2.원거리 3.지원");
        int choice = scanner.nextInt();
        scanner.nextLine();
        try {
            Mission mission = missionService.findById(missionId);
            if (mission.getHeroName().equals("")) {
                throw new IllegalArgumentException("배정된 히어로가 없습니다.");
            }
            BattleStrategy strategy = battleService.choose(choice);
            MissionLog log = missionLogService.record(mission.getTitle(), mission.getHeroName(), strategy.label(), strategy.damage(), strategy.teamScore());
            System.out.println("💥 전투를 마쳤습니다. " + log.getMissionTitle() + " / " + log.getHeroName() + " / " + log.getStrategyLabel() + " / 피해 " + log.getDamage() + " / 팀 " + log.getTeamScore());
        }  catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printLogs() {
        ArrayList<MissionLog> logs = missionLogService.findAll();
        System.out.println("📜 전투 로그");
        if (logs.size() == 0) {
            System.out.println("아직 전투 로그가 없습니다.");
        } else {
            for (MissionLog log : logs) {
                System.out.println(log.getId() + ". " + log.getMissionTitle() + " / " + log.getHeroName() + " / " + log.getStrategyLabel() + " / 피해 " + log.getDamage() + " / 팀 " + log.getTeamScore());
            }
        }
    }


    private void printHeroes() {
        ArrayList<Hero> heroes = heroService.findAll();
        System.out.println("🦸 히어로 목록");
        if (heroes.size() == 0) {
            System.out.println("아직 히어로가 없습니다.");
        } else {
            for (Hero hero : heroes) {
                System.out.println(hero.getId() + ". " + hero.getName());
            }
        }
    }

    private void printMissions() {
        ArrayList<Mission> missions = missionService.findAll();
        System.out.println("🎯 미션 목록");
        if (missions.size() == 0) {
            System.out.println("아직 미션이 없습니다.");
        } else {
            for (Mission mission : missions) {
                String heroName = mission.getHeroName();
                if (heroName.equals("")) {
                    heroName = "없음";
                }
                System.out.println(mission.getId() + ". " + mission.getTitle() + " / " + heroName);
            }
        }
    }
}
