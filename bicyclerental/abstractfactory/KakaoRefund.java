package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.TicketPurchasing;

public class KakaoRefund implements RefundProcessor{
  User user;
  int ticketId;
  double optionPrice;
  TicketPurchasing ticketPurchasing;

  public KakaoRefund(User user, int ticketId, double optionPrice){
    this.user = user;
    this.ticketId = ticketId;
    this.optionPrice = optionPrice;
  }
  public KakaoRefund(User user, int ticketId, TicketPurchasing ticketPurchasing){
    this.user = user;
    this.ticketId = ticketId;
    this.ticketPurchasing = ticketPurchasing;
  }
  @Override
  public void processRefund() { // 결제 완료 이후 환불
    System.out.println("=====카카오 페이 환불 창=====");
    System.out.println("사용자(ID) : "+ this.user.toString());
    System.out.println("티켓 ID : "+ this.ticketId);

    if (optionPrice != 0.0) {
      // 렌탈 용품 결제
      System.out.println("환불 금액 : " + optionPrice + "원");
    } else if (ticketPurchasing != null) {
      // 티켓 결제
      System.out.println("티켓 타입 : " + ticketPurchasing.getTicketName());
      System.out.println("결제 금액 : " + ticketPurchasing.calculateFinalPrice() + "원");
    }

    System.out.println("=====카카오 페이 환불 완료=====");
  }
}
