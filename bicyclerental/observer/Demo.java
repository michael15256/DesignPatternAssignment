package bicyclerental.observer;

public class Demo {
    public static void main(String[] args) {

        BikeRentalStation Jukjeon = new BikeRentalStation("S-001", "죽전역", "수지구", 10);
        BikeRentalStation Ori = new BikeRentalStation("S-002", "오리역", "분당구", 5);
        BikeRentalStation Migeum = new BikeRentalStation("S-003", "미금역", "분당구", 1);

        User user1 = new User(100, "김철수");
        User user2 = new User(101, "박영희");
        User user3 = new User(102, "홍길동");
        
        System.out.println("==============================\n");

        // 유저가 대여소를 구독
        user1.addFavorite(Jukjeon);
        user2.addFavorite(Jukjeon);
        user2.addFavorite(Ori);
        user3.addFavorite(Migeum);

        System.out.println("\n==============================\n");

        // 김철수가 죽전역에서 자전거를 빌림
        System.out.println("\n*** 1. 김철수가 죽전역에서 대여 ***");
        user1.rentBikeFrom(Jukjeon);
        
        System.out.println("\n==============================\n");

        // 박영희가 오리역에 반납
        System.out.println("\n*** 2. 박영희가 오리역에 반납 ***");
        user2.returnBikeTo(Ori);
        
        System.out.println("\n==============================\n");
        
        // 홍길동이 미금역에 대여
        System.out.println("\n*** 8. 홍길동이 미금역에서 대여 ***");
        user3.rentBikeFrom(Migeum);
        
        System.out.println("\n==============================\n");

        System.out.println("\n*** 9. 홍길동이 미금역(0대)에서 추가 대여 시도 ***");
        user3.rentBikeFrom(Migeum);

        System.out.println("\n==============================\n");

        System.out.println("\n*** 10. 홍길동이 미금역에 반납 ***");
        user3.returnBikeTo(Migeum);
    }
}