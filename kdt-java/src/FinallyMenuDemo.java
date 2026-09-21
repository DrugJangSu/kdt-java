public class FinallyMenuDemo {
    public void takeMenu(String menu) {
        if (menu == null || menu.equals("")) {
            throw new IllegalArgumentException("empty-menu");
        }
        System.out.println("menu=" + menu);
    }

    public void request(String menu) {
        try {
            takeMenu(menu);
        } catch (IllegalArgumentException ex) {
            System.out.println("reject=" + ex.getMessage());
        } finally {
            System.out.println("request=done");
        }
    }

    public static void main(String[] args) {
        FinallyMenuDemo app = new FinallyMenuDemo();
        app.request("");
        app.request("pork");
    }
}