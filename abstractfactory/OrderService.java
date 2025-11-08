package bicyclerental.abstractfactory;

public class OrderService {

  public void purchase(int userId, int ticketId, String paymentMethod) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      PaymentProcessor kakaoPaymentProcessor = kakaoPayFactory.createPaymentProcessor(userId, ticketId);
      kakaoPaymentProcessor.processPayment();
    }
  }

  public void cancelOrder(int userId, int ticketId, String paymentMethod) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      CancelProcessor kakaoCancelProcessor = kakaoPayFactory.createCancelProcessor(userId, ticketId);
      kakaoCancelProcessor.processCancel();
    }
  }

  public void refund(int userId, int ticketId, String paymentMethod) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      RefundProcessor kakaoRefundProcessor = kakaoPayFactory.createRefundProcessor(userId, ticketId);
      kakaoRefundProcessor.processRefund();
    }
  }

  public void registerSubscription(int userId, int ticketId, String paymentMethod) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      SubscriptionProcessor kakaoSubscriptionProcessor = kakaoPayFactory.createSubscriptionProcessor(userId, ticketId);
      kakaoSubscriptionProcessor.schedulePayment();
    }
  }
}