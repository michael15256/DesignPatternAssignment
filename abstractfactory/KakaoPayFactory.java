package bicyclerental.abstractfactory;

import bicyclerental.strategy.Purchasing;

public class KakaoPayFactory implements PaymentGatewayFactory{
  // 현재 KakaoPayment 객체가 많이 생성되니 -> Map 형식을 통해 변경 가능
  
  @Override
  public PaymentProcessor createPaymentProcessor(int userId, int ticketId, Purchasing purchasing) {
    PaymentProcessor kakaoPayment = new KakaoPayment(userId, ticketId, purchasing);
    return kakaoPayment;
  }

  @Override
  public CancelProcessor createCancelProcessor(int userId, int ticketId, Purchasing purchasing) {
    CancelProcessor kakaoCancel = new KakaoCancel(userId, ticketId, purchasing);
    return kakaoCancel;
  }

  @Override
  public RefundProcessor createRefundProcessor(int userId, int ticketId, Purchasing purchasing) {
    RefundProcessor kakaoRefund = new KakaoRefund(userId, ticketId, purchasing);
    return kakaoRefund;
  }

  @Override
  public SubscriptionProcessor createSubscriptionProcessor(int userId, int ticketId, Purchasing purchasing) {
    SubscriptionProcessor kakaoSubscription = new KakaoSubscription(userId, ticketId, purchasing);
    return kakaoSubscription;
  }

  @Override
  public void description() {
    System.out.println("======KakaoPay Service======");
  }
}
