import bicyclerental.decorator.Rental;
import bicyclerental.decorator.RentalOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        RentalPurchaseService service = new RentalPurchaseService();

        System.out.println("--- 헬멧, 바구니, 무릎 보호대 선택 ---");
        
        List<RentalOption> myOptions = Arrays.asList(
            RentalOption.HELMET, 
            RentalOption.BASKET,
            RentalOption.KNEEPAD
        );

        service.rentBike(1001, true, myOptions);


        System.out.println("\n--- 옵션 없이 대여 ---");
        
        service.rentBike(1002, true, List.of()); 
    }
}
