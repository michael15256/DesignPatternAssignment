package bicyclerental.strategy;

public class WeeklyDiscount implements DiscountStrategy {

    private final double DISCOUNT_RATE = 0.15;

    @Override
    public double calculateFinalPrice(Purchasing purchasing) {
        double basePrice = purchasing.getBasePrice();
        return basePrice * (1.0 - DISCOUNT_RATE);
    }
}