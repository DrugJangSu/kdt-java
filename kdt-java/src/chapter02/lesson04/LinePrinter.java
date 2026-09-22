package chapter02.lesson04;

public class LinePrinter {
    private final Printable target;

    public LinePrinter(Printable printable) {
        this.target = new LunchSlip();
    }

    public void run() {
        target.print();
    }
}
