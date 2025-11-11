package bicyclerental.abstractfactory;

import bicyclerental.observer.User;
import bicyclerental.strategy.Purchasing;

public class KakaoPayment implements PaymentProcessor{
  User user;
  int ticketId;
  Purchasing purchasing;

  public KakaoPayment(User user, int ticketId, Purchasing purchasing){
    this.user = user;
    this.ticketId = ticketId;
    this.purchasing = purchasing;
  }

  @Override
  public void processPayment() {
    System.out.println("=====카카오 페이 결제 창=====");
    System.out.println("사용자(ID) : "+ this.user.toString());
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("티켓 타입 : "+ purchasing.getTicketType());
    System.out.println("결제 명세 : "+ purchasing.getRental().getDescription());
    System.out.println("결제 금액 : " + purchasing.getRental().cost() + "원"); // x에 ticketId 를 통해서 가격 가져올 예정
    // 아예 User, Ticket 객체를 입력 받아서 정보를 빼서 써도 될 듯?
    System.out.println("=====카카오 페이 결제 완료=====");
  }
}
