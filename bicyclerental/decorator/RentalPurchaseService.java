package bicyclerental.decorator;

import bicyclerental.abstractfactory.OrderService;
import bicyclerental.decorator.*;
import bicyclerental.observer.User;
//import bicyclerental.abstractfactory.OrderService;
import java.util.List;

public class RentalPurchaseService {

    private OrderService orderService;

    public RentalPurchaseService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void rentBike(
        User user,
        boolean hasValidTicket,
        List<RentalOption> options
    ) {
        if (!hasValidTicket) {
            System.out.println("[오류] 유효한 이용권이 없습니다.");
            return;
        }

        Rental rental = new BaseRental(); 

        for (RentalOption option : options) {
            switch (option) {
                case HELMET -> rental = new Helmet(rental);
                case BASKET -> rental = new Basket(rental);
                case KNEEPAD -> rental = new KneePad(rental);
                case ELBOWPAD -> rental = new ElbowPad(rental);
                case GLOVES -> rental = new Gloves(rental);
            }
        }

        double optionPrice = rental.cost();
        System.out.println("대여 내역: " + rental.getDescription());
        System.out.println("추가 옵션 비용: " + optionPrice + "원");

        if (optionPrice > 0) {
            orderService.purchase(user, 999, "kakaoPay", optionPrice);
        } else {
            System.out.println("추가 비용 없음.");
        }
    }
}
