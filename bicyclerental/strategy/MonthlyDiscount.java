package bicyclerental.strategy;

public class MonthlyDiscount implements DiscountStrategy {

    private final double DISCOUNT_RATE = 0.20;

    @Override
    public double applyDiscount(double baseCost) {
        System.out.println("월간권 20% 할인 적용");
        return baseCost * (1.0 - DISCOUNT_RATE);
    }
}
