package bicyclerental.observer

import java.util.ArrayList;
import java.util.List;

public class BikeRentalStation implements Subject {
    private List<Observer> observers;
    
    private String stationId;
    private String name;
    private String location;
    private int remainingBike;
    private StationStatus stationStatus;

    public BikeRentalStation(String stationId, String name, String location, int initialBikes) {
        this.observers = new ArrayList<>();
        this.stationId = stationId;
        this.name = name;
        this.location = location;
        this.remainingBike = initialBikes;
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

    //대여소의 자체 기능
    public boolean rentBike() {
        if (stationStatus == StationStatus.EMPTY) {
            System.out.println(">> [" + name + "] 대여 실패 (재고 없음).");
            return false;
        }
        else if(stationStatus == StationStatus.MAINTENANCE){
            System.out.println(">> [" + name + "] 대여 실패 (점검 중).");
            return false;
        }
        
        remainingBike--;
        System.out.println(">> [" + name + "] 1대 대여. (현재 " + remainingBike + "대)");
        
        updateStatus();
        notifyObservers();
        return true;
    }

    public void returnBike() {
        if (stationStatus == StationStatus.FULL) {
            System.out.println(">> [" + name + "] 반납 실패 (가득 참).");
            return;
        }
        else if(stationStatus == StationStatus.MAINTENANCE){
            System.out.println(">> [" + name + "] 반납 실패 (점검 중).");
            return;
        }
        remainingBike++;
        System.out.println(">> [" + name + "] 1대 반납. (현재 " + remainingBike + "대)");

        updateStatus();
        notifyObservers();
    }

    // 대여소 상태를 갱신하는 내부 로직
    public void updateStatus() {
        if (this.stationStatus != StationStatus.MAINTENANCE) {
            if (remainingBike <= 0) {
                this.stationStatus = StationStatus.EMPTY;
            } else if (remainingBike >= 100) { // ex(최대 100대)
                this.stationStatus = StationStatus.FULL;
            } else {
                this.stationStatus = StationStatus.ACTIVE;
            }
        }
    }

    // 옵저버가 데이터를 PULL 할 수 있도록 설정

    public int getRemainingBike() {
        return this.remainingBike;
    }

    public String getName() {
        return this.name;
    }
    
    public StationStatus getStationStatus() {
        return this.stationStatus;
    }
}