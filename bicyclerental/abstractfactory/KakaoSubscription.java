package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

public class KakaoSubscription implements SubscriptionProcessor {
  User user;
  int ticketId;
  double optionPrice;
  TicketPurchasing ticketPurchasing;

  public KakaoSubscription(User user, int ticketId, double optionPrice){
    this.user = user;
    this.ticketId = ticketId;
    this.optionPrice = optionPrice;
  }
  public KakaoSubscription(User user, int ticketId, TicketPurchasing ticketPurchasing){
    this.user = user;
    this.ticketId = ticketId;
    this.ticketPurchasing = ticketPurchasing;
  }
  @Override
  public void schedulePayment() {
    System.out.println("=====카카오 페이 구독 결제 창=====");
    System.out.println("사용자(ID) : "+ this.user.toString());
    System.out.println("티켓 ID : "+ this.ticketId);

    if (optionPrice != 0.0) {
      // 렌탈 용품 결제
      System.out.println("구독 금액 : " + optionPrice + "원");
    } else if (ticketPurchasing != null) {
      // 티켓 결제
      System.out.println("티켓 타입 : " + ticketPurchasing.getTicketName());
      System.out.println("구독 금액 : " + ticketPurchasing.calculateFinalPrice() + "원");
    }

    System.out.println("=====카카오 페이 구독 결제 완료=====");
  }
}
