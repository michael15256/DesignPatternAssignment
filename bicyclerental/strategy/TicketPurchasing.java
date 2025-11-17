package bicyclerental.strategy;

public class TicketPurchasing {

    private final TicketPriceStrategy priceStrategy;
    private final DiscountStrategy discountStrategy;

    public TicketPurchasing(TicketPriceStrategy priceStrategy,
                            DiscountStrategy discountStrategy) {

        this.priceStrategy = priceStrategy;
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalPrice() {
        double baseCost = priceStrategy.getBaseCost();
        return discountStrategy.applyDiscount(baseCost);
    }

    public String getTicketName() {
        return priceStrategy.getTicketName();
    }
}
