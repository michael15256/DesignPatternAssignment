package bicyclerental.abstractfactory;

/*
import bicyclerental.decorator.BaseRental;
import bicyclerental.decorator.Rental;
import bicyclerental.strategy.DailyPrice;
import bicyclerental.strategy.HourlyPrice;
import bicyclerental.strategy.Purchasing;
import bicyclerental.strategy.TicketPriceStrategy;
*/

import bicyclerental.decorator.*;
import bicyclerental.strategy.*;
import bicyclerental.observer.*;

public class Demo {
  public static void main(String[] arg){
    // 유저 생성
    User user1 = new User(100, "김철수");
    User user2 = new User(101, "박영희");
    User user3 = new User(102, "홍길동");

    // 앱 당 하나 생성되는 orderService 객체
    OrderService orderService = new OrderService();
    // 티켓 가격 추가
    TicketPriceStrategy ticketPriceStrategyA = new DailyPrice();
    TicketPriceStrategy ticketPriceStrategyB = new HourlyPrice();

    // 대여 항목 추가
    Rental rentalA = new BaseRental(ticketPriceStrategyA);
    Rental rentalB = new BaseRental(ticketPriceStrategyB);

    // 구매 내역 추가
    Purchasing purchasingA = new Purchasing(rentalA, Purchasing.TicketType.DAILY);
    Purchasing purchasingB = new Purchasing(rentalB, Purchasing.TicketType.HOURLY);

    orderService.purchase(user1,100,"kakaoPay", purchasingA);
    orderService.cancelOrder(user1,100,"kakaoPay", purchasingA);
    orderService.refund(user2,100,"kakaoPay", purchasingA);
    orderService.registerSubscription(user2,100,"kakaoPay", purchasingA);

    orderService.purchase(user3,200,"kakaoPay", purchasingB);
    orderService.cancelOrder(user3,200,"kakaoPay", purchasingB);
    orderService.refund(user3,200,"kakaoPay", purchasingB);
    orderService.registerSubscription(user3,200,"kakaoPay", purchasingB);

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
