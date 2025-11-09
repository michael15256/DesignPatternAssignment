package bicyclerental.decorator;

public final class KneePad extends CondimentDecorator {

    private final double KNEE_PAD_COST = 200.0;
    private final String KNEE_PAD_NAME = "무릎 보호대";

    public KneePad(Rental rental) {
        super(rental);
    }

    @Override
    protected double addOnCost() {
        return KNEE_PAD_COST;
    }

    @Override
    protected String addOnDescription() {
        return KNEE_PAD_NAME;
    }
}
