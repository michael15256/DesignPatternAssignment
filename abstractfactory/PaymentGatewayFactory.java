package bicyclerental.abstractfactory;

public interface PaymentGatewayFactory {
  public PaymentProcessor createPaymentProcessor(int userId, int ticketId);
  public CancelProcessor createCancelProcessor(int userId, int ticketId);
  public RefundProcessor createRefundProcessor(int userId, int ticketId);
  public SubscriptionProcessor createSubscriptionProcessor(int userId, int ticketId);
  public void description();

}
