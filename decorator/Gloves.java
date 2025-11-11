package bicyclerental.decorator;

public final class Gloves extends CondimentDecorator {

    private final double GLOVES_COST = 300.0;
    private final String GLOVES_NAME = "장갑";

    public Gloves(Rental rental) {
        super(rental);
    }
    
    @Override
    protected double addOnCost() {
        return GLOVES_COST;
    }

    @Override
    protected String addOnDescription() {
        return GLOVES_NAME;
    }
}
