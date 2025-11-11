package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.Purchasing;

public class KakaoCancel implements CancelProcessor{
  User user;
  int ticketId;
  Purchasing purchasing;

  public KakaoCancel(User user, int ticketId, Purchasing purchasing){
    this.user = user;
    this.ticketId = ticketId;
    this.purchasing = purchasing;
  }

  @Override
  public void processCancel() { // 단순 결제 취소
    System.out.println("=====카카오 페이 취소 창=====");
    System.out.println("사용자(ID) : "+ this.user.toString());
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("티켓 타입 : "+ purchasing.getTicketType());
    System.out.println("결제 명세 : "+ purchasing.getRental().getDescription());
    System.out.println("취소 금액 : " + purchasing.getRental().cost() + "원");
    System.out.println("=====카카오 페이 취소 완료=====");
  }
}
