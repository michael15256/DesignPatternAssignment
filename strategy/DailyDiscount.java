package bicyclerental.strategy; 

public class DailyDiscount implements TicketStrategy {

    @Override
    public double getBaseCost() {
        return 5000.0; 
    }

    @Override
    public String getTicketName() {
        return "일일권";
    }
}
