package bicyclerental.abstractfactory;

/*
import bicyclerental.decorator.BaseRental;
import bicyclerental.decorator.Rental;
import bicyclerental.strategy.DailyPrice;
import bicyclerental.strategy.HourlyPrice;
import bicyclerental.strategy.Purchasing;
import bicyclerental.strategy.TicketPriceStrategy;
*/

import bicyclerental.strategy.HourlyPrice;
import bicyclerental.decorator.*;
import bicyclerental.strategy.*;
import bicyclerental.observer.*;

import java.util.Arrays;
import java.util.List;

public class Demo {
  public static void main(String[] arg){
    // 유저 생성
    User user1 = new User(100, "김철수");
    User user2 = new User(101, "박영희");
    User user3 = new User(102, "홍길동");

    // 앱 당 하나 생성되는 orderService 객체
    OrderService orderService = OrderService.INSTANCE;

    // 티켓 가격 추가
    TicketPriceStrategy dailyPriceStrategy = new DailyPrice();
    DiscountStrategy dailyDiscountStrategy = new DailyDiscount();

    // 티켓 구매 항목
    TicketPurchasing dailyTicketPurchasing = new TicketPurchasing(dailyPriceStrategy,dailyDiscountStrategy);

    // 대여 항목 추가


    // 티켓 구매
    System.out.println("-------------------------------티켓 구매 결제 과정-------------------------------");
    orderService.purchase(user1,100,"kakaoPay", dailyTicketPurchasing);
    orderService.cancelOrder(user1,100,"kakaoPay", dailyTicketPurchasing);
    orderService.refund(user2,100,"kakaoPay", dailyTicketPurchasing);
    orderService.registerSubscription(user2,100,"kakaoPay", dailyTicketPurchasing);

    // 렌탈 용품 구매
    System.out.println("-------------------------------렌탈 용품 결제 과정-------------------------------");
    RentalPurchaseService service = new RentalPurchaseService(orderService);

    System.out.println("----- 헬멧, 바구니, 무릎 보호대 선택 -----");

    List<RentalOption> myOptions = Arrays.asList(
      RentalOption.HELMET,
      RentalOption.BASKET,
      RentalOption.KNEEPAD
    );

    service.rentBike(user1, true, myOptions);

    System.out.println("\n----- 옵션 없이 대여 -----");

    service.rentBike(user2, true, List.of());

    System.out.println("-------------------------------옵저버 테스트-------------------------------");

    BikeRentalStation Jukjeon = new BikeRentalStation("S-001", "죽전역", "수지구", 10);
    BikeRentalStation Ori = new BikeRentalStation("S-002", "오리역", "분당구", 5);
    BikeRentalStation Migeum = new BikeRentalStation("S-003", "미금역", "분당구", 1);

    System.out.println("==============================\n");

    // 유저가 대여소를 구독
    user1.addFavorite(Jukjeon);
    user2.addFavorite(Jukjeon);
    user2.addFavorite(Ori);
    user3.addFavorite(Migeum);

    System.out.println("\n==============================\n");

    // 김철수가 죽전역에서 자전거를 빌림
    System.out.println("\n*** 1. 김철수가 죽전역에서 대여 ***");
    user1.rentBikeFrom(Jukjeon);

    System.out.println("\n==============================\n");

    // 박영희가 오리역에 반납
    System.out.println("\n*** 2. 박영희가 오리역에 반납 ***");
    user2.returnBikeTo(Ori);

    System.out.println("\n==============================\n");

    // 홍길동이 미금역에 대여
    System.out.println("\n*** 8. 홍길동이 미금역에서 대여 ***");
    user3.rentBikeFrom(Migeum);

    System.out.println("\n==============================\n");

    System.out.println("\n*** 9. 홍길동이 미금역(0대)에서 추가 대여 시도 ***");
    user3.rentBikeFrom(Migeum);

    System.out.println("\n==============================\n");

    System.out.println("\n*** 10. 홍길동이 미금역에 반납 ***");
    user3.returnBikeTo(Migeum);
  }
}
