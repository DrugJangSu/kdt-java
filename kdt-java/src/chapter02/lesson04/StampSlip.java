package chapter02.lesson04;

public class StampSlip implements Printable, Dated{
    @Override
    public void print() {
        System.out.println("stamp==ok");
    }

    @Override
    public String dayLabel() {
        return "friday";
    }





}
