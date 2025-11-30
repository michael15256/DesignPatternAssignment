package bicyclerental.observer;

import java.util.ArrayList;
import java.util.List;

import bicyclerental.strategy.TicketPurchasing;

public class User implements Observer {

    private int userId;
    private String name;
    
    private List<BikeRentalStation> favoriteStations;

    private TicketPurchasing activeTicket = null;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.favoriteStations = new ArrayList<>();
    }

    // Observer

    @Override
    public void update(BikeRentalStation station) {
        String stationName = station.getName();
        int remainingBike = station.getRemainingBike();
        StationStatus status = station.getStationStatus();

        System.out.println(
            "====== [" + this.name + "님] 즐겨찾기 알림 ====== \n" +
            "    " + stationName + "의 상태: " + status + "\n" +
            "    " + "현재 잔여 자전거: " + remainingBike + "대\n" +
            "====================================="
        );
        if (remainingBike == 2) {
            System.out.println("=====================================");
            System.out.println(stationName + " 자전거 2대 남음 !");
            System.out.println("=====================================");
        }
    }

    // 구독
    public void addFavorite(BikeRentalStation station) {
        this.favoriteStations.add(station);
        station.attach(this);
    }

    // 구독 취소
    public void removeFavorite(BikeRentalStation station) {
        this.favoriteStations.remove(station);
        station.detach(this);
    }
    
    // 대여
    public boolean rentBikeFrom(BikeRentalStation station) {
    System.out.println(this.name + "님이 " + station.getName() + "에 대여");
    
    boolean isSuccess = station.rentBike(); 
    
    return isSuccess; 
}

    // 반납
    public void returnBikeTo(BikeRentalStation station) {
        System.out.println(this.name + "님이 " + station.getName() + "에 반납");
        station.returnBike();
    }
    
    @Override
    public String toString() {
        return this.name + "(" + this.userId + ")"; 
    }

    public void activateTicket(TicketPurchasing ticket) {
        this.activeTicket = ticket;
        System.out.println("[알림] " + ticket.getTicketName() + " 사용이 시작되었습니다.");
    }

    public boolean hasActiveTicket() {
        return activeTicket != null;
    }

    public int getId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public List<BikeRentalStation> getMyFavorites() {
        return favoriteStations;
    }

    public TicketPurchasing getActiveTicket() {
        return activeTicket;
    }
}