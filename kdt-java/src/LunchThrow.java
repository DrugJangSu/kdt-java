public class LunchThrow {
    public void takeMenu(String menu) {
        if (menu == null || menu.equals("")) {
            throw new IllegalArgumentException("empty-menu");
        }
        System.out.println("menu=" + menu);
    }



    public void takeName(String name) throws IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("empty-name");
        }
    }

    public static void main(String[] args) {
        LunchThrow lunchThrow = new LunchThrow();
        lunchThrow.takeMenu("");        // 여기서 멈춤. 아래 줄은 실행 안됨
        lunchThrow.takeMenu("pork");
    }
}
