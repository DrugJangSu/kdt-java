package grocery;

import java.util.ArrayList;
import java.util.Scanner;

public class GroceryController {
    private final GroceryService groceryService;
    private final Scanner scanner;

    public GroceryController(GroceryService groceryService, Scanner scanner) {
        this.groceryService = groceryService;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        GroceryRepository repository = new GroceryRepository();
        GroceryService groceryService = new GroceryService(repository);
        Scanner scanner = new Scanner(System.in);
        GroceryController controller = new GroceryController(groceryService, scanner);
        controller.run();

    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();
            if (menu == 1) {
                registerItem();
            } else if (menu == 2) {
                printOne();
            } else if (menu == 3) {
                printAll();
            } else if (menu == 4) {
                changeQuantity();
            } else if (menu == 5) {
                deleteItem();
            } else if (menu == 0) {
                System.out.println("다음에 또 장 봐요.");
                running = false;
            } else {
                System.out.println("메뉴는 0부터 5까지입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("🌕 추석 장보기");
        System.out.println("1. 장 올리기");
        System.out.println("2. 장 하나 보기");
        System.out.println("3. 장 목록");
        System.out.println("4. 수량 바꾸기");
        System.out.println("5. 장 지우기");
        System.out.println("0. 종료");
        System.out.print("번호> ");
    }



    private void registerItem() {
        System.out.print("이름> ");
        String name = scanner.nextLine();
        System.out.print("수량> ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        try {
            GroceryItem item = groceryService.register(name, quantity);
            System.out.println("🛒 장을 올렸습니다. 번호 " + item.getId() + ", " + item.getName() + ", " + item.getQuantity() + "개");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printOne() {
        System.out.print("번호> ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            GroceryItem item = groceryService.findById(id);
            System.out.println(item.getId() + ", " + item.getName() + ", " + item.getQuantity() + "개");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAll() {
        ArrayList<GroceryItem> items = groceryService.findAll();
        System.out.println("🛒 장 목록");
        if (items.size() == 0) {
            System.out.println("아직 올린 장이 없습니다.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                GroceryItem item = items.get(i);
                System.out.println(item.getId() + ", " + item.getName() + " / " + item.getQuantity() + "개");
            }
        }
    }

    private void changeQuantity() {
        System.out.println("번호> ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("수량> ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        try {
            GroceryItem item = groceryService.changeQuantity(id, quantity);
            System.out.println("🛒 수량을 바꿨습니다. 번호 " + item.getId() + ", " + item.getName() + ", " + item.getQuantity() + "개");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteItem() {
        System.out.println("번호> ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
        groceryService.delete(id);
        System.out.println("🛒 장을 지웠습니다. 번호 " + id);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());}
    }
}











