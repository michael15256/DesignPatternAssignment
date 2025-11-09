package bicyclerental.strategy;

public class WeeklyDiscount implements TicketStrategy {

    @Override
    public double getBaseCost() {
        return 20000.0; 
    }

    @Override
    public String getTicketName() {
        return "주간권";
    }
}
