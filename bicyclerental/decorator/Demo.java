package bicyclerental.decorator;

import bicyclerental.abstractfactory.OrderService;
import bicyclerental.observer.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        OrderService orderService = OrderService.INSTANCE;
        RentalPurchaseService service = new RentalPurchaseService(orderService);

        System.out.println("--- 헬멧, 바구니, 무릎 보호대 선택 ---");
        
        List<RentalOption> myOptions = Arrays.asList(
            RentalOption.HELMET, 
            RentalOption.BASKET,
            RentalOption.KNEEPAD
        );
        User user1 = new User(100, "김철수");
        User user2 = new User(101, "박영희");

        service.rentBike(user1, true, myOptions);


        System.out.println("\n--- 옵션 없이 대여 ---");
        
        service.rentBike(user2, true, List.of());
    }
}
