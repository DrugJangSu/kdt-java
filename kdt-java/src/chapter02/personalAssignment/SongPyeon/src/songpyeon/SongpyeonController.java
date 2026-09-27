package songpyeon;

import java.util.ArrayList;
import java.util.Scanner;

public class SongpyeonController {
    private final FamilyMemberService familyMemberService;
    private final SongpyeonService songpyeonService;
    private final Scanner scanner;

    public SongpyeonController(FamilyMemberService familyMemberService, SongpyeonService songpyeonService, Scanner scanner) {
        this.familyMemberService = familyMemberService;
        this.songpyeonService = songpyeonService;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        FamilyMemberRepository familyMemberRepository = new FamilyMemberRepository();
        SongpyeonRepository songpyeonRepository = new SongpyeonRepository();
        FamilyMemberService familyMemberService = new FamilyMemberService(familyMemberRepository);
        SongpyeonService songpyeonService = new SongpyeonService(songpyeonRepository);
        Scanner scanner = new Scanner(System.in);
        SongpyeonController controller = new SongpyeonController(familyMemberService, songpyeonService, scanner);
        controller.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerFamily();
            } else if (menu == 2) {
                registerSongpyeon();
            } else if (menu == 3) {
                printSongpyeons();
            } else if (menu == 4) {
                printFamilies();
            } else if (menu == 0) {
                System.out.println("다음에 또 빚어요.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 4까지입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🌕 추석에 모인 가족");
        System.out.println("1. 가족 등록");
        System.out.println("2. 송편 빚기");
        System.out.println("3. 송편 목록");
        System.out.println("4. 가족 목록");
        System.out.println("0. 종료");
        System.out.print("번호> ");
    }

    private void registerFamily() {
        System.out.print("이름> ");
        String name = scanner.nextLine();
        try {
            FamilyMember member = familyMemberService.register(name);
            System.out.println("👵 가족을 등록했습니다. 번호 " + member.getId() + ", " + member.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void registerSongpyeon() {
        System.out.println("송편 이름> ");
        String name = scanner.nextLine();
        System.out.print("가족 번호> ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("빚기 스타일 1.예쁘게 2.빠르게> ");
        int styleChoice = scanner.nextInt();
        scanner.nextLine();
        try {
            FamilyMember member = familyMemberService.findById(memberId);
            Songpyeon songpyeon = songpyeonService.register(name, member.getName(), styleChoice);
            System.out.println("🥟 " + songpyeon.getName() + "을 빚었습니다. "
                    + songpyeon.getMakerName() + " / "
                    + songpyeon.getStyleLabel() + " / "
                    + songpyeon.getCount() + "개 / 모양 "
                    + songpyeon.getShapeScore() + "점");
        } catch (SongpyeonNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void printSongpyeons() {
        ArrayList<Songpyeon> songpyeons = songpyeonService.findAll();
        System.out.println("🥟 송편 목록");
        if (songpyeons.size() == 0) {
            System.out.println("아직 빚은 송편이 없습니다.");
        } else {
            for (Songpyeon songpyeon : songpyeons) {
                System.out.println(songpyeon.getId() + ". "
                        + songpyeon.getName() + " / "
                        + songpyeon.getMakerName() + " / "
                        + songpyeon.getStyleLabel() + " / "
                        + songpyeon.getCount() + "개 / 모양 "
                        + songpyeon.getShapeScore() + "점");
            }
        }
    }
    private void printFamilies() {
        ArrayList<FamilyMember> members = familyMemberService.findAll();
        System.out.println("🌕 가족 목록");
        if (members.size() == 0) {
            System.out.println("아직 모인 가족이 없습니다.");
        } else {
            for (FamilyMember member : members) {
                System.out.println(member.getId() + ". " + member.getName());
            }
        }
    }
}
