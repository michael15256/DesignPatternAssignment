package bicyclerental.observer;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BikeRentalStation implements Subject {
    private List<Observer> observers;
    
    private String stationId;
    private String name;
    private String location;
    private List<Bike> bikelist;
    private StationStatus stationStatus;

    public BikeRentalStation(String stationId, String name, String location, int initialBikes) {
        this.observers = new ArrayList<>();
        this.stationId = stationId;
        this.name = name;
        this.location = location;

        this.bikelist = new ArrayList<>();
        for (int i = 0; i < initialBikes; i++) {
            this.bikelist.add(new Bike(stationId + "-" + (i + 1))); //바이크 아이디 ex) S-001-1
        }

        this.updateStatus(); 
    }

    //Subject 인터페이스
    @Override
    public void attach(Observer observer) {
        System.out.println(">> [" + name + "]에 " + observer + " 구독");
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        System.out.println(">> [" + name + "]에 " + observer + " 구독 취소");
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println(">> [" + name + "] 상태 변경 구독자들에게 알림 발송");
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    //대여소의 자체 기능 - 렌트
    public boolean rentBike() {

        if(stationStatus == StationStatus.MAINTENANCE){
            System.out.println(">> [" + name + "] 대여 실패 (점검 중).");
            return false;
        }

        Optional<Bike> bikeToRent = bikelist.stream().filter(Bike::isAvailable).findFirst();
        
        if (bikeToRent.isPresent()){
            bikeToRent.get().rent();

            System.out.println(">> [" + name + "] 자전거 " + bikeToRent.get().getBikeId() + " 대여 완료.");

            updateStatus();
            notifyObservers();
            return true;
        }
        else{
            System.out.println(">> [" + name + "] 대여 실패 (재고 없음).");
            return false;
        }
    }
    //반납  
    public void returnBike() {
            
        if(stationStatus == StationStatus.MAINTENANCE){
            System.out.println(">> [" + name + "] 반납 실패 (점검 중).");
            return;
        }

        Optional<Bike> bikeToReturn = bikelist.stream().filter(bike -> !bike.isAvailable()).findFirst();

        if(bikeToReturn.isPresent()){
            bikeToReturn.get().returnToStation();
            System.out.println(">> [" + name + "] 자전거 " + bikeToReturn.get().getBikeId() + " 반납 완료.");

            updateStatus();
            notifyObservers();
        }
        else{
            System.out.println(">> [" + name + "] 반납 실패 (가득 참).");
            return;
        }
    }

    // 대여소 상태를 갱신하는 내부 로직
    public void updateStatus() {

        int availableBikes = this.getRemainingBike();

        if (this.stationStatus != StationStatus.MAINTENANCE) {
            if (availableBikes <= 0) {
                this.stationStatus = StationStatus.EMPTY;
            } else if (availableBikes >= bikelist.size()) { 
                this.stationStatus = StationStatus.FULL;
            } else {
                this.stationStatus = StationStatus.ACTIVE;
            }
        }
    }


    public int getRemainingBike() {
        return (int) bikelist.stream().filter(Bike::isAvailable).count();
    }

    public String getName() {
        return this.name;
    }
    
    public StationStatus getStationStatus() {
        return this.stationStatus;
    }
}