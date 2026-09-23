package chapter02.chicken;

public class InvalidGradeException extends IllegalArgumentException {
    public InvalidGradeException(String grade) {
        super("올바르지 않은 등급입니다: " + grade);
    }
}
