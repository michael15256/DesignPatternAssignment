package bicyclerental.decorator;
import java.util.Objects;

public abstract class CondimentDecorator implements Rental {
    protected final Rental rental;

    public CondimentDecorator(Rental rental) {
        this.rental = Objects.requireNonNull(rental);
    }

    @Override
    public double cost(){
        return rental.cost() + addOnCost();
    }

    @Override
    public String getDescription(){
        return rental.getDescription() + ", " + addOnDescription();
    }
    
    protected abstract double addOnCost();
    protected abstract String addOnDescription();
}
