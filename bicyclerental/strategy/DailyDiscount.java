package bicyclerental.strategy;

public class DailyDiscount implements DiscountStrategy {

    private final double DISCOUNT_RATE = 0.10;

    @Override
    public double applyDiscount(double baseCost) {
        System.out.println("일일권 10% 할인 적용");
        return baseCost * (1.0 - DISCOUNT_RATE);
    }
}
