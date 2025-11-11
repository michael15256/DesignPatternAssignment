package bicyclerental.decorator;

public final class Basket extends CondimentDecorator {
    private final double BASKET_COST = 300.0;
    private final String BASKET_NAME = "바구니";

    public Basket(Rental rental) {
        super(rental);
    }

    @Override
    protected double addOnCost() {
        return BASKET_COST;
    }
    
    @Override
    protected String addOnDescription() {
        return BASKET_NAME;
    }
}