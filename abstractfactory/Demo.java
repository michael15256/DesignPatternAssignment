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

public class Demo {
  public static void main(String[] arg){
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

    orderService.purchase(100,100,"kakaoPay", purchasingA);
    orderService.cancelOrder(100,100,"kakaoPay", purchasingA);
    orderService.refund(100,100,"kakaoPay", purchasingA);
    orderService.registerSubscription(100,100,"kakaoPay", purchasingA);

    orderService.purchase(200,200,"kakaoPay", purchasingB);
    orderService.cancelOrder(200,200,"kakaoPay", purchasingB);
    orderService.refund(200,200,"kakaoPay", purchasingB);
    orderService.registerSubscription(200,200,"kakaoPay", purchasingB);
  }
}
