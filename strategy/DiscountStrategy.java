package strategy;
//purchasing 객체를 받아 최종 결제 금액(할인)을 계산

public interface DiscountStrategy {
    
    double calculateFinalPrice(Purchasing purchasing);
}
