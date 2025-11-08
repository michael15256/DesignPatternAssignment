package bicyclerental.abstractfactory;

public class Demo {
  public static void main(String[] arg){
    // 앱 당 하나 생성되는 orderService 객체
    OrderService orderService = new OrderService();
    orderService.purchase(100,100,"kakaoPay");
    orderService.cancelOrder(100,100,"kakaoPay");
    orderService.refund(100,100,"kakaoPay");
    orderService.registerSubscription(100,100,"kakaoPay");
  }
}
