package bicyclerental.abstractfactory;

import bicyclerental.strategy.Purchasing;

public interface PaymentGatewayFactory {
  public PaymentProcessor createPaymentProcessor(int userId, int ticketId, Purchasing purchasing);
  public CancelProcessor createCancelProcessor(int userId, int ticketId,Purchasing purchasing);
  public RefundProcessor createRefundProcessor(int userId, int ticketId,Purchasing purchasing);
  public SubscriptionProcessor createSubscriptionProcessor(int userId, int ticketId,Purchasing purchasing);
  public void description();

}
