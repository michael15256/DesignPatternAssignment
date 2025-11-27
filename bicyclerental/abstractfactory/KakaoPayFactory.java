package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

public class KakaoPayFactory implements PaymentGatewayFactory{
  // 현재 KakaoPayment 객체가 많이 생성되니 -> Map 형식을 통해 변경 가능
  
  @Override
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, double optionPrice) {
    PaymentProcessor kakaoPayment = new KakaoPayment(user, ticketId, optionPrice);
    return kakaoPayment;
  }
  @Override
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, TicketPurchasing ticketPurchasing) {
    PaymentProcessor kakaoPayment = new KakaoPayment(user, ticketId, ticketPurchasing);
    return kakaoPayment;
  }

  @Override
  public CancelProcessor createCancelProcessor(User user, int ticketId, double optionPrice) {
    CancelProcessor kakaoCancel = new KakaoCancel(user, ticketId, optionPrice);
    return kakaoCancel;
  }
  @Override
  public CancelProcessor createCancelProcessor(User user, int ticketId, TicketPurchasing ticketPurchasing) {
    CancelProcessor kakaoCancel = new KakaoCancel(user, ticketId, ticketPurchasing);
    return kakaoCancel;
  }

  @Override
  public RefundProcessor createRefundProcessor(User user, int ticketId, double optionPrice) {
    RefundProcessor kakaoRefund = new KakaoRefund(user, ticketId, optionPrice);
    return kakaoRefund;
  }
  @Override
  public RefundProcessor createRefundProcessor(User user, int ticketId, TicketPurchasing ticketPurchasing) {
    RefundProcessor kakaoRefund = new KakaoRefund(user, ticketId, ticketPurchasing);
    return kakaoRefund;
  }
  @Override
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId, double optionPrice) {
    SubscriptionProcessor kakaoSubscription = new KakaoSubscription(user, ticketId, optionPrice);
    return kakaoSubscription;
  }
  @Override
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId, TicketPurchasing ticketPurchasing) {
    SubscriptionProcessor kakaoSubscription = new KakaoSubscription(user, ticketId, ticketPurchasing);
    return kakaoSubscription;
  }

  @Override
  public void description() {
    System.out.println("======KakaoPay Service======");
  }
}
