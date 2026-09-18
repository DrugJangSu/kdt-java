public class Student {
    int no; // 학생 번호
    String name; // 이름
    String subject; // 수강 과목
    int score; // 점수

    public void setInfo(int pNo, String pName, String pSubject, int pScore) {
        no = pNo;
        name = pName;
        subject = pSubject;
        score = pScore;

    }
    public void printInfo() {
        System.out.println(no + "/" + name + "/" + subject + "/" + score);
    }

}

