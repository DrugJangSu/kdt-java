public class AttendanceNameThrowQuiz {
    public static void takeName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException();
        }
        System.out.println("name=" + name);
    }


    public static void main(String[] args) {
        AttendanceNameThrowQuiz app = new AttendanceNameThrowQuiz();
        app.takeName(null);
        app.takeName("kim");
    }
}
