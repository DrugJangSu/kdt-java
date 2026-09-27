package guest;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestController {
    private final GuestService guestService;
    private final Scanner scanner;

    public GuestController(GuestService guestService, Scanner scanner) {
        this.guestService = guestService;
        this.scanner = scanner;
    }


    public static void main(String[] args) {
        GuestRepository guestRepository = new GuestRepository();
        GuestService guestService = new GuestService(guestRepository);
        Scanner scanner = new Scanner(System.in);
        GuestController controller = new GuestController(guestService, scanner);
        controller.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerGuest();
            } else if (menu == 2) {
                printAll();
            } else if (menu == 3) {
                printOne();
            } else if (menu == 0) {
                System.out.println("다음에 또 만나요.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 3까지입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🌕 추석 초대");
        System.out.println("1. 이름 올리기");
        System.out.println("2. 명단 보기");
        System.out.println("3. 번호로 찾기");
        System.out.println("0. 종료");
        System.out.print("번호> ");
    }

    private void registerGuest() {
        System.out.print("이름> ");
        String name = scanner.nextLine();
        try {
            Guest guest = guestService.register(name);
            System.out.println("🌕 명단에 올렸습니다. 번호 " + guest.getId() + ", " +
                    guest.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAll() {
        ArrayList<Guest> guests = guestService.findAll();
        System.out.println("🌕 초대 명단");
        if (guests.size() == 0) {
            System.out.println("아직 명단이 비어 있습니다.");
        } else {
            for (int i = 0; i < guests.size(); i++) {
                Guest guest = guests.get(i);
                System.out.println(guest.getId() + ". " + guest.getName());
            }
        }
    }

    private void printOne() {
        System.out.print("번호> ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Guest guest = guestService.findById(id);
            System.out.println(guest.getId() + ". " + guest.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
