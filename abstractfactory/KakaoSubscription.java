package bicyclerental.abstractfactory;

public class KakaoSubscription implements SubscriptionProcessor {
  int userId;
  int ticketId;

  public KakaoSubscription(int userId, int ticketId){
    this.userId = userId;
    this.ticketId = ticketId;
  }
  @Override
  public void schedulePayment() {
    System.out.println("=====카카오 페이 결제 창=====");
    System.out.println("사용자 ID : "+ this.userId);
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("결제 금액 : " + "x" + "원");
    System.out.println("=====카카오 페이 결제 완료=====");
  }
}
