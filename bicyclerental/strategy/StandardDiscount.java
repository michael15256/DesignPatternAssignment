package bicyclerental.strategy;

public class StandardDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double baseCost) {
        return baseCost;  // 할인 없음
    }
}
