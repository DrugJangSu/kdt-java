package chapter02.lesson04;

public class DualRoleApp {

    public static void main(String[] args) {
        StampSlip stampSlip = new StampSlip();

        Printable printable = stampSlip;
        Dated dated = stampSlip;

        printable.print();

        System.out.println("day=" + dated.dayLabel());

    }

}
