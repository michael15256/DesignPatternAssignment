package bicyclerental.observer;

public class Bike{
    private String bikeId;
    private BikeStatus status;

    public Bike(String bikeId) {
        this.bikeId = bikeId;
        this.status = BikeStatus.AVAILABLE;
    }

    public void rent() {
        this.status = BikeStatus.RENTED;
    }

    public void returnToStation() {
        this.status = BikeStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return this.status == BikeStatus.AVAILABLE;
    }

    public String getBikeId() {
        return bikeId;
    }
}