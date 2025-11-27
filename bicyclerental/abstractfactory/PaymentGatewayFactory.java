package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

public interface PaymentGatewayFactory {
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, double optionPrice);
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, TicketPurchasing ticketPurchasing);
  public CancelProcessor createCancelProcessor(User user, int ticketId,double optionPrice);
  public CancelProcessor createCancelProcessor(User user, int ticketId,TicketPurchasing ticketPurchasing);
  public RefundProcessor createRefundProcessor(User user, int ticketId,double optionPrice);
  public RefundProcessor createRefundProcessor(User user, int ticketId,TicketPurchasing ticketPurchasing);
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId,double optionPrice);
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId,TicketPurchasing ticketPurchasing);
  public void description();

}
