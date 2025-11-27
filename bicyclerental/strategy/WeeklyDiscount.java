package bicyclerental.strategy;

public class WeeklyDiscount implements DiscountStrategy {

    private final double DISCOUNT_RATE = 0.15;

    @Override
    public double applyDiscount(double baseCost) {
        System.out.println("주간권 15% 할인 적용");
        return baseCost * (1.0 - DISCOUNT_RATE);
    }
}
