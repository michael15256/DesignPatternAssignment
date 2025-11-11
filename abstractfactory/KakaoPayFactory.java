package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.Purchasing;

public class KakaoPayFactory implements PaymentGatewayFactory{
  // 현재 KakaoPayment 객체가 많이 생성되니 -> Map 형식을 통해 변경 가능
  
  @Override
  public PaymentProcessor createPaymentProcessor(User user, int ticketId, Purchasing purchasing) {
    PaymentProcessor kakaoPayment = new KakaoPayment(user, ticketId, purchasing);
    return kakaoPayment;
  }

  @Override
  public CancelProcessor createCancelProcessor(User user, int ticketId, Purchasing purchasing) {
    CancelProcessor kakaoCancel = new KakaoCancel(user, ticketId, purchasing);
    return kakaoCancel;
  }

  @Override
  public RefundProcessor createRefundProcessor(User user, int ticketId, Purchasing purchasing) {
    RefundProcessor kakaoRefund = new KakaoRefund(user, ticketId, purchasing);
    return kakaoRefund;
  }

  @Override
  public SubscriptionProcessor createSubscriptionProcessor(User user, int ticketId, Purchasing purchasing) {
    SubscriptionProcessor kakaoSubscription = new KakaoSubscription(user, ticketId, purchasing);
    return kakaoSubscription;
  }

  @Override
  public void description() {
    System.out.println("======KakaoPay Service======");
  }
}
