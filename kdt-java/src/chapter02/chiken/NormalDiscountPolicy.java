package chapter02.chiken;

public class NormalDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return (int) (price * 0.9);
    }
}
