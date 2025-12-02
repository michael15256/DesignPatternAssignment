package bicyclerental.test;

import bicyclerental.abstractfactory.OrderService;
import bicyclerental.decorator.*;
import bicyclerental.observer.*;
import bicyclerental.strategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo2 {

    //DB
    private static List<BikeRentalStation> stations = new ArrayList<>();
    private static Map<Integer, User> userDatabase = new HashMap<>(); 
    private static Map<Integer, List<TicketPurchasing>> userTickets = new HashMap<>(); 
    

    private static User currentUser; 
    private static OrderService orderService = OrderService.INSTANCE;
    private static Scanner scanner = new Scanner(System.in, "CP949");

    public static void main(String[] args) {
        setup();

        if (!loginProcess()) {
            return;
        }

        while (true) {
            printMainMenu();
            int cmd = input();

            switch (cmd) {
                case 1: 
                    ticketMenu();
                    break;
                case 2: 
                    bikeMenu();
                    break;
                case 3: 
                    favoriteStations(); 
                    break;
                case 4: 
                    if (currentUser.getId() == 123) {
                        adminMenu();
                    } else {
                        System.out.println("\n[접근 거부] 관리자 계정만 접근 가능합니다.");
                    }
                    break;
                case 0:
                    System.out.println("시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void setup() {
        stations.add(new BikeRentalStation("S-001", "죽전역", "수지구", 10));
        stations.add(new BikeRentalStation("S-002", "오리역", "분당구", 5));
        stations.add(new BikeRentalStation("S-003", "미금역", "분당구", 1));

        User preUser = new User(32224772, "choijiwoo");
        userDatabase.put(32224772, preUser);
        
        User admin = new User(123, "admin");
        userDatabase.put(123, admin);
    }

    static boolean loginProcess() {
        System.out.println("==================== 로그인 ====================");
        System.out.print("사용자 ID를 입력하세요(숫자): ");
        int userId = input();

        currentUser = userDatabase.get(userId);

        if (currentUser == null) {
            System.out.println("등록된 ID가 없습니다.");
            System.out.print("회원가입 하시겠습니까? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y") || choice.equals("yes")) {
                System.out.print("이름 입력: ");
                String userName = scanner.nextLine();
                currentUser = new User(userId, userName);
                userDatabase.put(userId, currentUser);
                System.out.println("회원가입이 완료되었습니다: " + currentUser);
                return true;
            } 
            else {
                System.out.println("회원가입이 취소되었습니다. 프로그램을 종료합니다.");
                return false;
            }
        } 
        else {
            System.out.println("\n[로그인 성공] 기존 회원입니다: " + currentUser);
            return true;
        }
    }

    static void printMainMenu() {
        System.out.println("\n====== 메인 메뉴 ======");
        System.out.println("1. 이용권 관리");
        System.out.println("2. 자전거 관리");
        System.out.println("3. 즐겨찾기 관리");
        System.out.println("4. [관리자] 대여소 관리");
        System.out.println("0. 종료");
        System.out.println("=======================");
        System.out.print("선택: ");
    }

    //이용권 관리
    static void ticketMenu() {
        while(true) {
            System.out.println("\n=== [ 이용권 관리 ] ===");
            System.out.println("1. 이용권 구매");
            System.out.println("2. 이용권 사용");
            System.out.println("3. 내 이용권 확인");
            System.out.println("0. 뒤로가기");
            System.out.println("========================");
            System.out.print("선택: ");
            
            int cmd = input();
            if (cmd == 0) return;

            switch(cmd) {
                case 1: 
                    buyTicket(); 
                    break;
                case 2: 
                    useTickets(); 
                    break;
                case 3: 
                    myTickets(); 
                    break;
                default: 
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void buyTicket() {
        System.out.println("\n[ 이용권 구매 ]");
        System.out.println("1. 시간권 (1,000원)");
        System.out.println("2. 일일권 (5,000원)");
        System.out.println("3. 주간권 (20,000원)");
        System.out.println("4. 월간권 (60,000원)");
        System.out.print("선택(취소:0): ");

        int ticketChoice = input();
        if (ticketChoice == 0) {
            return;
        }

        TicketPriceStrategy priceStrategy;
        DiscountStrategy discountStrategy;
        String ticketName;

        switch (ticketChoice) {
            case 1:
                priceStrategy = new HourlyPrice();
                discountStrategy = new StandardDiscount();
                ticketName = "시간권";
                break;
            case 2:
                priceStrategy = new DailyPrice();
                discountStrategy = new DailyDiscount();
                ticketName = "일일권";
                break;
            case 3:
                priceStrategy = new WeeklyPrice();
                discountStrategy = new WeeklyDiscount();
                ticketName = "주간권";
                break;
            case 4:
                priceStrategy = new MonthlyPrice();
                discountStrategy = new MonthlyDiscount();
                ticketName = "월간권";
                break;
            default:
                System.out.println("\n[구매 실패] 잘못된 티켓 선택입니다. 구매가 취소되었습니다.");
                return;
        }

        TicketPurchasing ticketPurchasing = new TicketPurchasing(priceStrategy, discountStrategy);
        System.out.println("\n선택 티켓: " + ticketName);
        System.out.println("최종 가격: " + ticketPurchasing.calculateFinalPrice() + "원");

        System.out.println("\n==================== 결제수단 선택 ====================");
        System.out.println("1. 카카오페이 (kakaoPay)");
        System.out.print("결제수단을 선택하세요 (취소:0): ");
        int paymentChoice = input();

        String paymentMethod;
        switch (paymentChoice) {
          case 1:
            paymentMethod = "kakaoPay";
            break;
          default:
            System.out.println("\n[구매 실패] 잘못된 결제수단 선택입니다. 구매가 취소되었습니다.");
            return;
        }

        System.out.println("선택한 결제수단: " + paymentMethod);

        System.out.println("\n==================== 티켓 구매 결제 과정 ====================");
        int ticketId = 100;
        orderService.purchase(currentUser, ticketId, paymentMethod, ticketPurchasing);

        userTickets.putIfAbsent(currentUser.getId(), new ArrayList<>());
        userTickets.get(currentUser.getId()).add(ticketPurchasing);
        System.out.println("\n[알림] " + ticketName + "이(가) 보유 티켓에 추가되었습니다.");
    }

    static void useTickets(){
        List<TicketPurchasing> ownedTickets = userTickets.getOrDefault(currentUser.getId(), new ArrayList<>());
        
        if (currentUser.hasActiveTicket()) {
            System.out.println("[알림] 이미 사용 중인 이용권이 있습니다.");
            System.out.println("현재 적용 중: " + currentUser.getActiveTicket().getTicketName());
            return;
        }
        else if (ownedTickets.isEmpty()) {
            System.out.println("\n[알림] 보유한 이용권이 없습니다.");
            System.out.print("티켓을 구매하시겠습니까? (y/n): ");
            String purchaseChoice = scanner.nextLine().trim().toLowerCase();

            if (purchaseChoice.equals("y") || purchaseChoice.equals("yes")) {
                buyTicket();
            } 
            else {
                System.out.println("이용권 사용이 취소되었습니다.");
                return;
            }
        } 
        else {
            System.out.println("\n============ 사용할 이용권 선택 ============");
            for (int i = 0; i < ownedTickets.size(); i++) {
                System.out.println((i + 1) + ". " + ownedTickets.get(i).getTicketName());
            }
            System.out.print("선택(취소:0): ");
            int idx = input();

            if(idx == 0){
                return;
            }
            if(idx < 1 || idx > ownedTickets.size()){
                System.out.println("잘못된 번호입니다.");
                return;
            }

            TicketPurchasing selectedTicket = ownedTickets.get(idx - 1);

            currentUser.activateTicket(selectedTicket);

            ownedTickets.remove(selectedTicket);

            System.out.println("\n" + selectedTicket.getTicketName() + "을 사용합니다.");
            return;
        }
    }

    static void myTickets() {
        System.out.println("\n[ 내 이용권 목록 ]");
        List<TicketPurchasing> tickets = userTickets.get(currentUser.getId()); 
        
        if (tickets == null || tickets.isEmpty()) {
            System.out.println("보유 중인 이용권이 없습니다.");
        } else {
            for (int i = 0; i < tickets.size(); i++) {
                System.out.println((i + 1) + ". " + tickets.get(i).getTicketName()); 
            }
        }
    }

    //자전거 관리
    static void bikeMenu() {
        while(true) {
            System.out.println("\n=== [ 자전거 관리 ] ===");
            System.out.println("1. 자전거 대여");
            System.out.println("2. 자전거 반납");
            System.out.println("0. 뒤로가기");
            System.out.println("=======================");
            System.out.print("선택: ");
            
            int cmd = input();
            if (cmd == 0) return;

            switch(cmd) {
                case 1: 
                    rentBike(); 
                    break;
                case 2: 
                    returnBike(); 
                    break;
                default: 
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void rentBike() {
        System.out.println("\n[ 자전거 대여 ]");
        

        if (!currentUser.hasActiveTicket()) {
            System.out.println("[알림] 현재 사용 중인 이용권이 없습니다.");
            
            System.out.print("이용권 관리 메뉴로 이동하시겠습니까? (y / n): ");
            String move = scanner.nextLine().trim().toLowerCase();
            if (move.equals("y") || move.equals("yes")) {
                ticketMenu();
            }
            return;
        }
        
        TicketPurchasing selectedTicket = currentUser.getActiveTicket();
        System.out.println(">> [적용 중인 티켓]: " + selectedTicket.getTicketName());

        BikeRentalStation station = selectStation(stations);
        if (station == null) {
            return;
        }

        Rental rental = new BaseRental();
        
        while (true) {
            System.out.println("\n=== 추가 물품 대여 (유료) ===");
            System.out.println("1. 헬멧(+500원)");
            System.out.println("2. 바구니(+1000원)");
            System.out.println("3. 보호대(+1500원)");
            System.out.println("4. 결제(선택완료)");
            System.out.print("선택(취소: 0): ");
            int opt = input();
            
            if (opt == 0) {
                return;
            }
            if (opt == 4) {
                break;
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
                    System.out.println("잘못된 번호입니다");
            }
            System.out.println("현재 추가 요금: " + rental.cost() + "원");
        }

        double optionCost = rental.cost();
        
        System.out.println("\n========== [ 최종 결제 및 대여 ] ==========");
        System.out.println("대여 장소: " + station.getName());
        System.out.println("사용 티켓: " + selectedTicket.getTicketName());
        System.out.println("대여 물품: " + rental.getDescription());
        System.out.println("물품 비용: " + optionCost + "원");
        System.out.println("=========================================");

        if (optionCost > 0) {
            System.out.print("물품 비용을 결제하시겠습니까? (1.네 / 2.아니요): ");
            int pay = input();
            if (pay != 1) {
                System.out.println("결제를 취소하여 대여가 중단됩니다.");
                return;
            }

            System.out.println("\n==================== 결제수단 선택 ====================");
            System.out.println("1. 카카오페이 (kakaoPay)");
            System.out.print("결제수단을 선택하세요 (취소:0): ");
            int paymentChoice = input();

            String paymentMethod;
            switch (paymentChoice) {
                case 1:
                    paymentMethod = "kakaoPay";
                    break;
                default:
                    System.out.println("\n[구매 실패] 잘못된 결제수단 선택입니다. 구매가 취소되었습니다.");
                    return;
            }
            orderService.purchase(currentUser, 000, paymentMethod, optionCost);
            System.out.println("물품 비용 결제 완료");
        }

        if (currentUser.rentBikeFrom(station)) {
            System.out.println("자전거 대여가 완료되었습니다.");
        } else {
             System.out.println("대여 실패");
        }
    }

    static void returnBike() {
        System.out.println("\n[ 자전거 반납 ]");
        BikeRentalStation s = selectStation(stations);
        if (s != null) {
            currentUser.returnBikeTo(s);
            System.out.println("반납 처리가 완료되었습니다.");
        }
    }

    //즐겨찾기 관리
    static void favoriteStations() {
        while(true) {
            System.out.println("\n[ 즐겨찾기 관리 ]");
            System.out.println("1. 등록");
            System.out.println("2. 취소");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            int cmd = input();
            
            if (cmd == 0) {
                return;
            }

            if (cmd == 1) {
                BikeRentalStation s = selectStation(stations);
                if (s != null) {
                    currentUser.addFavorite(s);
                }
            } 
            else if (cmd == 2) {
                List<BikeRentalStation> my = currentUser.getMyFavorites();
                if (my.isEmpty()) {
                    System.out.println("즐겨찾기 목록이 비어있습니다.");
                } 
                else {
                    BikeRentalStation s = selectStation(my);
                    if (s != null) {
                        currentUser.removeFavorite(s);
                    }
                }
            }
        }
    }

    //관리자 메뉴
    static void adminMenu() {
        while (true) {
            System.out.println("\n===== [ 관리자 페이지 ] =====");
            System.out.println("1. 대여소 추가");
            System.out.println("2. 상태 변경");
            System.out.println("3. 재고 관리");
            System.out.println("0. 뒤로가기");
            System.out.print("선택: ");
            
            int cmd = input();
            if (cmd == 0) {
                return;
            }

            if (cmd == 1) {
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("위치: ");
                String loc = scanner.nextLine();
                System.out.print("개수: ");
                int cnt = input();

                String id = "S-" + String.format("%03d", stations.size() + 1);
                stations.add(new BikeRentalStation(id, name, loc, cnt));
                System.out.println("등록 완료.");
            } 
            else if (cmd == 2) {
                BikeRentalStation s = selectStation(stations);
                if (s != null) {
                    System.out.println("현재 상태: " + s.getStationStatus());
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
            else if (cmd == 3) {
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
        }
    }

    
    static BikeRentalStation selectStation(List<BikeRentalStation> list) {
        if (list.isEmpty()) {
            System.out.println("목록이 없습니다.");
            return null;
        }
        System.out.println("======= 대여소 목록 =======");
        for (int i = 0; i < list.size(); i++) {
            BikeRentalStation s = list.get(i);
            System.out.printf("%d. %s (%s, %s, %d대)\n", 
                i+1, s.getName(), s.getLocation(), s.getStationStatus(), s.getRemainingBike());
        }
        System.out.println("=========================");
        System.out.print("선택(0.취소): ");
        int idx = input();
        if (idx < 1 || idx > list.size()) {
            return null;
        }
        return list.get(idx - 1);
    }

    static int input() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } 
        catch (Exception e) {
            return -1;
        }
    }
}