package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

public class KakaoCancel implements CancelProcessor{
  User user;
  int ticketId;
  double optionPrice;
  TicketPurchasing ticketPurchasing;

  public KakaoCancel(User user, int ticketId, double optionPrice){
    this.user = user;
    this.ticketId = ticketId;
    this.optionPrice = optionPrice;
  }
  public KakaoCancel(User user, int ticketId, TicketPurchasing ticketPurchasing){
    this.user = user;
    this.ticketId = ticketId;
    this.ticketPurchasing = ticketPurchasing;
  }

  @Override
  public void processCancel() { // 단순 결제 취소
    System.out.println("=====카카오 페이 취소 창=====");
    System.out.println("사용자(ID) : "+ this.user.toString());
    System.out.println("티켓 ID : "+ this.ticketId);

    if (optionPrice != 0.0) {
      // 렌탈 용품 결제
      System.out.println("취소 금액 : " + optionPrice + "원");
    } else if (ticketPurchasing != null) {
      // 티켓 결제
      System.out.println("티켓 타입 : " + ticketPurchasing.getTicketName());
      System.out.println("취소 금액 : " + ticketPurchasing.calculateFinalPrice() + "원");
    }

    System.out.println("=====카카오 페이 취소 완료=====");
  }
}
