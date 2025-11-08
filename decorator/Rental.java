package bicyclerental.strategy;
//가격 계산 + 설명 제공 인터페이스

public interface Rental {
    double cost();
    String getDescription();
}
