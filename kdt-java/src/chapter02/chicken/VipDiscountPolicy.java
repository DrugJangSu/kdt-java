package chapter02.chicken;

public class VipDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return (int) (price * 0.9);
    }
}
