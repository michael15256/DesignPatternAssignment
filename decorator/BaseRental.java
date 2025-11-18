package bicyclerental.decorator;

public class BaseRental implements Rental {

    @Override
    public double cost() {
        return 0.0; 
    }

    @Override
    public String getDescription() {
        return "자전거 대여";
    }
}
