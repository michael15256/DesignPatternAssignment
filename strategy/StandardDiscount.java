package bicyclerental.strategy;

public class StandardDiscount implements DiscountStrategy {

    @Override
    public double calculateFinalPrice(Purchasing purchasing) {
        return purchasing.getBasePrice();
    }
}
