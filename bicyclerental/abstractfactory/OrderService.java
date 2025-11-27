package bicyclerental.abstractfactory;
import bicyclerental.decorator.Rental;
import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

/*
*  티겟 가격은 strategy.Purchasing 객체를 받아옴.
*  Rental 의 cost()를 사용, getDescription() 은 보류
*  TicketType 사용
* */

public enum OrderService {
  INSTANCE; // 싱글턴 적용

  public void purchase(User user, int ticketId, String paymentMethod, double optionPrice) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      PaymentProcessor kakaoPaymentProcessor = kakaoPayFactory.createPaymentProcessor(user, ticketId, optionPrice);
      kakaoPaymentProcessor.processPayment();
    }
  }
  public void purchase(User user, int ticketId, String paymentMethod, TicketPurchasing ticketPurchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      PaymentProcessor kakaoPaymentProcessor = kakaoPayFactory.createPaymentProcessor(user, ticketId, ticketPurchasing);
      kakaoPaymentProcessor.processPayment();
    }
  }

  public void cancelOrder(User user, int ticketId, String paymentMethod, double optionPrice) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      CancelProcessor kakaoCancelProcessor = kakaoPayFactory.createCancelProcessor(user, ticketId, optionPrice);
      kakaoCancelProcessor.processCancel();
    }
  }
  public void cancelOrder(User user, int ticketId, String paymentMethod, TicketPurchasing ticketPurchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      CancelProcessor kakaoCancelProcessor = kakaoPayFactory.createCancelProcessor(user, ticketId, ticketPurchasing);
      kakaoCancelProcessor.processCancel();
    }
  }

  public void refund(User user, int ticketId, String paymentMethod, double optionPrice) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      RefundProcessor kakaoRefundProcessor = kakaoPayFactory.createRefundProcessor(user, ticketId, optionPrice);
      kakaoRefundProcessor.processRefund();
    }
  }
  public void refund(User user, int ticketId, String paymentMethod, TicketPurchasing ticketPurchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      RefundProcessor kakaoRefundProcessor = kakaoPayFactory.createRefundProcessor(user, ticketId, ticketPurchasing);
      kakaoRefundProcessor.processRefund();
    }
  }

  public void registerSubscription(User user, int ticketId, String paymentMethod, double optionPrice) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      SubscriptionProcessor kakaoSubscriptionProcessor = kakaoPayFactory.createSubscriptionProcessor(user, ticketId, optionPrice);
      kakaoSubscriptionProcessor.schedulePayment();
    }
  }
  public void registerSubscription(User user, int ticketId, String paymentMethod, TicketPurchasing ticketPurchasing) {
    if (paymentMethod.equals("kakaoPay")) {
      PaymentGatewayFactory kakaoPayFactory = new KakaoPayFactory();
      SubscriptionProcessor kakaoSubscriptionProcessor = kakaoPayFactory.createSubscriptionProcessor(user, ticketId, ticketPurchasing);
      kakaoSubscriptionProcessor.schedulePayment();
    }
  }
}