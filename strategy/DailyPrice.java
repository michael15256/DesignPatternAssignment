package bicyclerental.strategy;

public class DailyPrice implements TicketPriceStrategy {
    @Override
    public double getBaseCost() {
        return 5000.0;
    }
    @Override
    public String getTicketName() {
        return "일일권";
    }
    
}
