package strategy;
import decorator.Rental;
/*교수님 과제 Booking class와 대응
전략과 데코레이터를 거쳐 모든 옵션이 포함된 Rental 객체 가격 가져옴
최종 가격 + 티켓 종류
 */

public class Purchasing {
    public enum TicketType {
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY
    }

    private final Rental rental; //데코레이터로 완성된 rental 객체
    private final TicketType ticketType;

    public Purchasing(Rental rental, TicketType ticketType) {
        this.rental = rental;
        this.ticketType = ticketType;
    }

    public Rental getRental() {return rental;}
    public TicketType getTicketType() {return ticketType;}

    public double getBasePrice() {
        return rental.cost();
    }
}