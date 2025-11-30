package bicyclerental;

import bicyclerental.decorator.*;
import bicyclerental.observer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    static List<BikeRentalStation> stations = new ArrayList<>();
    static User user; 
    static Scanner sc = new Scanner(System.in, "CP949");

    public static void main(String[] args) {
        setup(); 

        while (true) {
            System.out.println("------ 메인 메뉴 ------");
            System.out.println("1. 자전거 대여");
            System.out.println("2. 자전거 반납");
            System.out.println("3. 즐겨찾기 등록");
            System.out.println("4. 즐겨찾기 취소");
            System.out.println("5. [관리자] 대여소 관리");
            System.out.println("0. 종료");
            System.out.println("-----------------------");
            System.out.print("선택: ");
            
            int cmd = input();

            switch (cmd) {
                case 1: 
                    rentBike(); 
                    break;
                case 2: 
                    returnBike(); 
                    break;
                case 3: 
                    addStation(); 
                    break;
                case 4: 
                    delStation(); 
                    break;
                case 5: 
                    adminMenu(); 
                    break;
                case 0:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("다시 입력하세요.");
            }
        }
    }

    static void setup() {
        stations.add(new BikeRentalStation("S-001", "죽전역", "수지구", 10));
        stations.add(new BikeRentalStation("S-002", "오리역", "분당구", 10));
        stations.add(new BikeRentalStation("S-003", "미금역", "분당구", 10));
        user = new User(123, "관리자");
    }

    // 1. 대여
    static void rentBike() {
        System.out.println("[ 대여 ]");
        BikeRentalStation s = selectStation(stations);
        if (s == null) return;

        Rental rental = new BaseRental();

        while (true) {
            System.out.println("------ 옵션 ------");
            System.out.println("1. 헬멧");
            System.out.println("2. 바구니");
            System.out.println("3. 보호대");
            System.out.println("4. 결제(선택 안함)");
            System.out.println("0. 뒤로가기");
            System.out.println("------------------");
            System.out.print("선택: ");
            int opt = input();
            if (opt == 4) {
                break;
            }
            else if(opt == 0){
                return;
            }


            switch (opt) {
                case 1: 
                    rental = new Helmet(rental); 
                    break;
                case 2: 
                    rental = new Basket(rental); 
                    break;
                case 3: 
                    rental = new KneePad(rental); 
                    break;
                default: 
                    System.out.println("잘못된 번호 입니다.");
            }
            System.out.println("현재금액: " + rental.cost() + "원");
        }

        System.out.println("\n--- 영수증 ---");
        System.out.println("장소: " + s.getName());
        System.out.println("옵션: " + rental.getDescription());
        System.out.println("합계: " + rental.cost() + "원");
        
        user.rentBikeFrom(s);
    }

    // 2. 반납
    static void returnBike() {
        System.out.println("[ 반납 ]");
        BikeRentalStation s = selectStation(stations);
        if (s != null) {
            user.returnBikeTo(s);
            System.out.println("반납했습니다.");
        }
    }

    // 3. 즐겨찾기
    static void addStation() {
        System.out.println("[ 즐겨찾기 등록 ]");
        BikeRentalStation s = selectStation(stations);
        if (s != null) {
            user.addFavorite(s);
        }
    }

    // 4. 즐겨찾기 취소
    static void delStation() {
        System.out.println("[ 즐겨찾기 취소 ]");
        List<BikeRentalStation> my = user.getMyFavorites();
        if (my.isEmpty()) {
            System.out.println("목록이 없습니다.");
            return;
        }
        BikeRentalStation s = selectStation(my);
        if (s != null) {
            user.removeFavorite(s);
        }
    }

    // 5. 관리자 메뉴
    static void adminMenu() {
        while (true) {
            System.out.println("----- [ 관리자 페이지 ] -----");
            System.out.println("1. 대여소 추가");
            System.out.println("2. 상태 변경");
            System.out.println("3. 재고 관리");
            System.out.println("0. 뒤로가기");
            System.out.println("-----------------------------");
            System.out.print("선택: ");
            
            int cmd = input();
            if (cmd == 0) {
                return;
            }

            if (cmd == 1) {
                System.out.print("이름: ");
                String name = sc.nextLine();
                System.out.print("위치: ");
                String loc = sc.nextLine();
                System.out.print("개수: ");
                int cnt = input();

                String id = "S-" + String.format("%03d", stations.size() + 1);
                stations.add(new BikeRentalStation(id, name, loc, cnt));
                System.out.println("등록 완료.");
            } 
            else if (cmd == 2) { // 상태 변경
                BikeRentalStation s = selectStation(stations);
                if (s != null) {
                    System.out.println("현재: " + s.getStationStatus());
                    StationStatus[] vals = StationStatus.values();
                    for(int i=0; i<vals.length; i++) 
                        System.out.print((i+1) + "." + vals[i] + "  ");
                    System.out.println();
                    
                    System.out.print("변경할 상태 번호: ");
                    int idx = input();
                    if (idx >= 1 && idx <= vals.length) {
                        s.setStationStatus(vals[idx-1]);
                    } else {
                        System.out.println("잘못된 번호");
                    }
                }
            }
            else if (cmd == 3) { // 재고 관리
                BikeRentalStation s = selectStation(stations);
                if (s != null) {
                    System.out.println("현재 재고: " + s.getRemainingBike());
                    System.out.print("변경할 수량: ");
                    int cnt = input();
                    if (cnt >= 0) {
                        s.setRemainingBike(cnt); 
                        System.out.println("변경 완료.");
                    } else {
                        System.out.println("0 이상 입력하세요.");
                    }
                }
            }
            else {
                System.out.println("잘못된 입력");
            }
        }
    }

    // 유틸
    static BikeRentalStation selectStation(List<BikeRentalStation> list) {
        if (list.isEmpty()) {
            System.out.println("목록 없음");
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            BikeRentalStation s = list.get(i);
            System.out.printf("%d.%s(%s, %s, %d대)\n", 
                i+1, s.getName(), s.getLocation(), s.getStationStatus(), s.getRemainingBike());
        }
        System.out.print("선택(0.취소): ");
        int idx = input();
        if (idx < 1 || idx > list.size()) {
            return null;
        }
        return list.get(idx - 1);
    }

    static int input() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}