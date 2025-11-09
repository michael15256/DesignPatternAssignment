package bicyclerental.strategy;
//기본 가격 결정할 전략 클래스
public interface TicketPriceStrategy {
    double getBaseCost();
    String getTicketName();
}
