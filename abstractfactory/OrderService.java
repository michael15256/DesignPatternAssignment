package bicyclerental.abstractfactory;
import bicyclerental.decorator.Rental;
import bicyclerental.observer.User;
import bicyclerental.strategy.Purchasing;

/*
*  티겟 가격은 strategy.Purchasing 객체를 받아옴.
*  Rental 의 cost()를 사용, getDescription() 은 보류
*  TicketType 사용
* */

public class OrderService {

  public void purchase(User user, int ticketId, String paymentMethod, Purchasing purchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      PaymentProcessor kakaoPaymentProcessor = kakaoPayFactory.createPaymentProcessor(user, ticketId, purchasing);
      kakaoPaymentProcessor.processPayment();
    }
  }

  public void cancelOrder(User user, int ticketId, String paymentMethod, Purchasing purchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      CancelProcessor kakaoCancelProcessor = kakaoPayFactory.createCancelProcessor(user, ticketId, purchasing);
      kakaoCancelProcessor.processCancel();
    }
  }

  public void refund(User user, int ticketId, String paymentMethod, Purchasing purchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      RefundProcessor kakaoRefundProcessor = kakaoPayFactory.createRefundProcessor(user, ticketId, purchasing);
      kakaoRefundProcessor.processRefund();
    }
  }

  public void registerSubscription(User user, int ticketId, String paymentMethod, Purchasing purchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      SubscriptionProcessor kakaoSubscriptionProcessor = kakaoPayFactory.createSubscriptionProcessor(user, ticketId, purchasing);
      kakaoSubscriptionProcessor.schedulePayment();
    }
  }
}