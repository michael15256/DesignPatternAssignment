package bicyclerental.abstractfactory;

import bicyclerental.strategy.*;
import bicyclerental.observer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo {
  private static Map<Integer, User> userDatabase = new HashMap<>();
  private static Map<Integer, List<TicketPurchasing>> userTickets = new HashMap<>();

  public static void main(String[] arg) {
    User preRegisteredUser = new User(32224772, "choijiwoo");
    userDatabase.put(32224772, preRegisteredUser);

    Scanner scanner = new Scanner(System.in);

    System.out.println("==================== 로그인 ====================");
    System.out.print("사용자 ID를 입력하세요: ");
    int userId = scanner.nextInt();
    scanner.nextLine();

    System.out.print("사용자 이름을 입력하세요: ");
    String userName = scanner.nextLine();

    User currentUser = userDatabase.get(userId);
    if (currentUser == null) {
      System.out.println("\n[알림] 등록된 사용자가 없습니다.");
      System.out.print("회원가입을 진행하시겠습니까? (y/n): ");
      String signupChoice = scanner.nextLine().trim().toLowerCase();

      if (signupChoice.equals("y") || signupChoice.equals("yes")) {
        currentUser = new User(userId, userName);
        userDatabase.put(userId, currentUser);
        System.out.println("회원가입이 완료되었습니다: " + currentUser);
      } else {
        System.out.println("회원가입이 취소되었습니다. 프로그램을 종료합니다.");
        scanner.close();
        return;
      }
    } else {
      System.out.println("\n[로그인 성공] 기존 회원입니다: " + currentUser);
    }

    OrderService orderService = OrderService.INSTANCE;

    boolean running = true;
    while (running) {
      System.out.println("\n==================== 메뉴 선택 ====================");
      System.out.println("1. 티켓 구매");
      System.out.println("2. 이용권 사용");
      System.out.println("3. 종료");

      int menuChoice = scanner.nextInt();
      scanner.nextLine();

      if (menuChoice == 2) {
        List<TicketPurchasing> ownedTickets = userTickets.getOrDefault(userId, new ArrayList<>());

        if (ownedTickets.isEmpty()) {
          System.out.println("\n[알림] 보유한 이용권이 없습니다.");
          System.out.print("티켓을 구매하시겠습니까? (y/n): ");
          String purchaseChoice = scanner.nextLine().trim().toLowerCase();

          if (purchaseChoice.equals("y") || purchaseChoice.equals("yes")) {
            menuChoice = 1;
          } else {
            System.out.println("이용권 사용이 취소되었습니다.");
            continue;
          }
        } else {
          System.out.println("\n==================== 보유 이용권 ====================");
          for (int i = 0; i < ownedTickets.size(); i++) {
            TicketPurchasing ticket = ownedTickets.get(i);
            System.out.println((i + 1) + ". " + ticket.getTicketName());
          }
          System.out.println("\n이용권을 사용합니다.");
          continue;
        }
      }

      if (menuChoice == 1) {
        System.out.println("\n==================== 티켓 선택 ====================");
        System.out.println("1. 시간권 (1,000원)");
        System.out.println("2. 일일권 (5,000원)");
        System.out.println("3. 주간권 (20,000원)");
        System.out.println("4. 월간권 (60,000원)");
        System.out.print("구매할 티켓을 선택하세요 (1-4): ");

        int ticketChoice = scanner.nextInt();
        scanner.nextLine();

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
            continue; // 메뉴로 돌아가기
        }

        TicketPurchasing ticketPurchasing = new TicketPurchasing(priceStrategy, discountStrategy);
        System.out.println("\n선택한 티켓: " + ticketName);
        System.out.println("최종 가격: " + ticketPurchasing.calculateFinalPrice() + "원");

        System.out.println("\n==================== 결제수단 선택 ====================");
        System.out.println("1. 카카오페이 (kakaoPay)");
        System.out.print("결제수단을 선택하세요 (1): ");

        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        String paymentMethod;
        switch (paymentChoice) {
          case 1:
            paymentMethod = "kakaoPay";
            break;
          default:
            System.out.println("잘못된 선택입니다.");
            continue; // 메뉴로 돌아가기
        }

        System.out.println("선택한 결제수단: " + paymentMethod);

        System.out.println("\n==================== 티켓 구매 결제 과정 ====================");
        int ticketId = 100;
        orderService.purchase(currentUser, ticketId, paymentMethod, ticketPurchasing);

        userTickets.putIfAbsent(userId, new ArrayList<>());
        userTickets.get(userId).add(ticketPurchasing);
        System.out.println("\n[알림] " + ticketName + "이(가) 보유 티켓에 추가되었습니다.");
      } else if (menuChoice == 3) {
        System.out.println("\n프로그램을 종료합니다.");
        running = false;
      } else {
        System.out.println("\n[오류] 잘못된 메뉴 선택입니다.");
      }
    }

    scanner.close();

  }
}
