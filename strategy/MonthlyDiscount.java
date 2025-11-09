package bicyclerental.strategy; 

public class MonthlyDiscount implements TicketStrategy {

    @Override
    public double getBaseCost() {
        return 60000.0;
    }

    @Override
    public String getTicketName() {
        return "월간권";
    }
}
