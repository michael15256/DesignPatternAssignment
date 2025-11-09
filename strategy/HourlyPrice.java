package bicyclerental.strategy;

public class HourlyPrice implements TicketPriceStrategy {
    @Override
    public double getBaseCost() {
        return 1000.0;
    }
    @Override
    public String getTicketName() {
        return "시간권";
    }
}
