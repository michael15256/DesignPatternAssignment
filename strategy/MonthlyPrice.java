package bicyclerental.strategy;

public class MonthlyPrice implements TicketPriceStrategy {

    @Override
    public double getBaseCost() {
        return 60000.0; 
    }

    @Override
    public String getTicketName() {
        return "월간권";
    }
}