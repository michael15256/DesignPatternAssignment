package bicyclerental.strategy;
import bicyclerental.strategy.*;
import bicyclerental.abstractfactory.OrderService;
import bicyclerental.decorator.*;

public class TicketPurchaseService {
    private OrderService orderService;

    public TicketPurchaseService() {
        this.orderService = new OrderService();
    }

    public void purchaseTicket(int userId, TicketStrategy strategy, String paymentMethod, boolean useHelmet, boolean useBasket) {
        Rental rental = new BaseRental(strategy);
        if (useHelmet) {
            rental = new Helmet(rental);
        }
        if (useBasket) {
            rental = new Basket(rental);
        }
        double finalPrice = rental.cost();
        int TicketId = 100;
        orderService.purchase(userId, TicketId, "kakaoPay", finalPrice);

    }
}
