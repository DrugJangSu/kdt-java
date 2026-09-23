package chapter02.chicken;

public class NormalDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return price;
    }
}
