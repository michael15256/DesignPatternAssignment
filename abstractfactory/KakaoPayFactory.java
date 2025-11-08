package bicyclerental.abstractfactory;

public class KakaoPayFactory implements PaymentGatewayFactory{
  // 현재 KakaoPayment 객체가 많이 생성되니 -> Map 형식을 통해 변경 가능
  
  @Override
  public PaymentProcessor createPaymentProcessor(int userId, int ticketId) {
    PaymentProcessor kakaoPayment = new KakaoPayment(userId, ticketId);
    return kakaoPayment;
  }

  @Override
  public CancelProcessor createCancelProcessor(int userId, int ticketId) {
    CancelProcessor kakaoCancel = new KakaoCancel(userId, ticketId);
    return kakaoCancel;
  }

  @Override
  public RefundProcessor createRefundProcessor(int userId, int ticketId) {
    RefundProcessor kakaoRefund = new KakaoRefund(userId, ticketId);
    return kakaoRefund;
  }

  @Override
  public SubscriptionProcessor createSubscriptionProcessor(int userId, int ticketId) {
    SubscriptionProcessor kakaoSubscription = new KakaoSubscription(userId, ticketId);
    return kakaoSubscription;
  }

  @Override
  public void description() {
    System.out.println("======KakaoPay Service======");
  }
}
