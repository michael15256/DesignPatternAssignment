package bicyclerental.decorator; 
import bicyclerental.strategy.TicketStrategy;
import java.util.Objects;

public class BaseRental implements Rental { 
    private final TicketStrategy strategy;

    public BaseRental(TicketStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public double cost() {
        return this.strategy.getBaseCost();
    }

    @Override
    public String getDescription() {
        return this.strategy.getTicketName();
    }
}
