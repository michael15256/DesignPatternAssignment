package bicyclerental.decorator;

public final class Helmet extends CondimentDecorator {

    private final double HELMET_COST = 500.0;
    private final String HELMET_NAME = "헬멧";

    public Helmet(Rental rental) {
        super(rental);
    }

    @Override
    protected double addOnCost() {
        return HELMET_COST;
    }
    
    @Override
    protected String addOnDescription() {
        return HELMET_NAME;
    }
}