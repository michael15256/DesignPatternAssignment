package bicyclerental.observer

import java.util.ArrayList;
import java.util.List;

public class User implements Observer {

    private int userId;
    private String name;
    
    //관심 대여소 등록
    private List<BikeRentalStation> favoriteStations;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.favoriteStations = new ArrayList<>();
    }

    // Observer 인터페이스

    @Override
    public void update(BikeRentalStation station) {
        String stationName = station.getName();
        int remainingBike = station.getRemainingBike();
        StationStatus status = station.getStationStatus();

        System.out.println(
            "--- [" + this.name + "님] 즐겨찾기 알림 --- \n" +
            "    " + stationName + "의 상태: " + status + "\n" +
            "    " + "현재 잔여 자전거: " + remainingBike + "대\n" +
            "-------------------------------------"
        );
    }

    // 유저가 대여소를 구독
    public void addFavorite(BikeRentalStation station) {
        this.favoriteStations.add(station);
        station.attach(this);
    }

    // 유저가 구독 취소
    public void removeFavorite(BikeRentalStation station) {
        this.favoriteStations.remove(station);
        station.detach(this);
    }
    
    // 유저가 대여소에서 자전거를 빌리는 행위
    public void rentBikeFrom(BikeRentalStation station) {
        System.out.println(this.name + "님이 " + station.getName() + "에서 대여 시도");
        station.rentBike();
    }

    // 유저가 대여소에 자전거를 반납하는 행위
    public void returnBikeTo(BikeRentalStation station) {
        System.out.println(this.name + "님이 " + station.getName() + "에 반납 시도");
        station.returnBike();
    }
    
    @Override
    public String toString() {
        return this.name + "(" + this.userId + ")"; 
    }
}