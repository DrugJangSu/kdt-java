package chapter02.lesson04;

public abstract class CafeItem {

    public abstract int price();

    public void printWon() {
        System.out.println("won = " + price());
    }

}

