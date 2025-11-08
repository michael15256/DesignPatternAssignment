package bicyclerental.abstractfactory;

public class KakaoRefund implements RefundProcessor{
  int userId;
  int ticketId;

  public KakaoRefund(int userId, int ticketId){
    this.userId = userId;
    this.ticketId = ticketId;
  }
  @Override
  public void processRefund() { // 결제 완료 이후 환불
    System.out.println("=====카카오 페이 환불 창=====");
    System.out.println("사용자 ID : "+ this.userId);
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("환불 금액 : " + "x" + "원");
    System.out.println("=====카카오 페이 환불 완료=====");
  }
}
