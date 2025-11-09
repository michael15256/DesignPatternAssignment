package bicyclerental.abstractfactory;

import bicyclerental.strategy.Purchasing;

public class KakaoRefund implements RefundProcessor{
  int userId;
  int ticketId;
  Purchasing purchasing;

  public KakaoRefund(int userId, int ticketId, Purchasing purchasing){
    this.userId = userId;
    this.ticketId = ticketId;
    this.purchasing = purchasing;
  }
  @Override
  public void processRefund() { // 결제 완료 이후 환불
    System.out.println("=====카카오 페이 환불 창=====");
    System.out.println("사용자 ID : "+ this.userId);
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("티켓 타입 : "+ purchasing.getTicketType());
    System.out.println("결제 명세 : "+ purchasing.getRental().getDescription());
    System.out.println("환불 금액 : " + purchasing.getRental().cost() + "원");
    System.out.println("=====카카오 페이 환불 완료=====");
  }
}
