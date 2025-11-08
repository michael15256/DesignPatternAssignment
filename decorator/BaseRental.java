package decorator;

import strategy.TicketPriceStrategy;
import java.util.Objects;

public final class BaseRental implements Rental {
    private final TicketPriceStrategy ticketPriceStrategy; //기본 가격 가져옴

    public BaseRental(TicketPriceStrategy ticketPriceStrategy) {
        this.ticketPriceStrategy = Objects.requireNonNull(ticketPriceStrategy);
    } 

    @Override
    public double cost() {
        return ticketPriceStrategy.getBaseCost();
    }

    @Override
    public String getDescription() {
        return ticketPriceStrategy.getTicketName();
    }
    
}
