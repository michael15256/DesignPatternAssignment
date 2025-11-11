package bicyclerental.decorator; 

public final class ElbowPad extends CondimentDecorator {

    private final double ELBOW_PAD_COST = 200.0;
    private final String ELBOW_PAD_NAME = "팔꿈치 보호대";

    public ElbowPad(Rental rental) {
        super(rental);
    }
    
    @Override
    protected double addOnCost() {
        return ELBOW_PAD_COST;
    }

    @Override
    protected String addOnDescription() {
        return ELBOW_PAD_NAME;
    }
}
