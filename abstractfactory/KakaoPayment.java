package bicyclerental.abstractfactory;

public class KakaoPayment implements PaymentProcessor{
  int userId;
  int ticketId;

  public KakaoPayment(int userId, int ticketId){
    this.userId = userId;
    this.ticketId = ticketId;
  }

  @Override
  public void processPayment() {
    System.out.println("=====카카오 페이 결제 창=====");
    System.out.println("사용자 ID : "+ this.userId);
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("결제 금액 : " + "x" + "원"); // x에 ticketId 를 통해서 가격 가져올 예정
    // 아예 User, Ticket 객체를 입력 받아서 정보를 빼서 써도 될 듯?
    System.out.println("=====카카오 페이 결제 완료=====");
  }
}
