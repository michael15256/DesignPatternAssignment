package bicyclerental.abstractfactory;

public class KakaoCancel implements CancelProcessor{
  int userId;
  int ticketId;

  public KakaoCancel(int userId, int ticketId){
    this.userId = userId;
    this.ticketId = ticketId;
  }

  @Override
  public void processCancel() { // 단순 결제 취소
    System.out.println("=====카카오 페이 취소 창=====");
    System.out.println("사용자 ID : "+ this.userId);
    System.out.println("티켓 ID : "+ this.ticketId);
    System.out.println("취소 금액 : " + "x" + "원"); // x에 ticketId 를 통해서 가격 가져올 예정
    System.out.println("=====카카오 페이 취소 완료=====");
  }
}
