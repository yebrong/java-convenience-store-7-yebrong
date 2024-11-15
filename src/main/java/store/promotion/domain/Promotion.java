package store.promotion.domain;

import java.time.LocalDateTime;

import camp.nextstep.edu.missionutils.DateTimes;
import store.order.domain.CartItem;
import store.product.domain.Product;

public enum Promotion {
    CARBONATED2_1("탄산2+1",2, 1, LocalDateTime.of(2024, 1, 1,0,0,0), LocalDateTime.of(2024, 12, 31,0,0,0)),
    MD_RECOMMENDED("MD추천상품",1, 1, LocalDateTime.of(2024, 1, 1,0,0,0), LocalDateTime.of(2024, 12, 31,0,0,0)),
    FLASH_DISCOUNT("반짝할인",1, 1, LocalDateTime.of(2024, 11, 1,0,0,0), LocalDateTime.of(2024, 11, 30,0,0,0));

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;


    Promotion(String name, int buy, int get, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}
