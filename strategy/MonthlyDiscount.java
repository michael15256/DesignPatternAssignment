package bicyclerental.strategy;

public class MonthlyDiscount implements DiscountStrategy {

    private final double DISCOUNT_RATE = 0.20

    @Override
    public double calculateFinalPrice(Purchasing purchasing) {
        double basePrice = purchasing.getBasePrice();
        return basePrice * (1.0 - DISCOUNT_RATE);
    }
}
