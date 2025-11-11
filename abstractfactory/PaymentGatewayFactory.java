package bicyclerental.abstractfactory;

import bicyclerental.strategy.Purchasing;
import bicyclerental.observer.User;

public interface PaymentGatewayFactory {
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, Purchasing purchasing);
  public CancelProcessor createCancelProcessor(User user, int ticketId,Purchasing purchasing);
  public RefundProcessor createRefundProcessor(User user, int ticketId,Purchasing purchasing);
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId,Purchasing purchasing);
  public void description();

}
