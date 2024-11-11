package store.promotion.domain;

import java.time.LocalDate;
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

    public boolean addFreeProduct(CartItem cartItem, Promotion promotion) {
        int totalQuantity = cartItem.getQuantity();
        return totalQuantity - promotion.getBuy() - calculateNumberOfPromotionalProducts(cartItem, promotion) < 0;
    }

    public Promotion checkPromotion(Product product){
        Promotion promotion = isPromotion(product);
        if (promotion != null && isInPromotionPeriod(promotion)) {
            return promotion;
        }
        return null;
    }

    public int calculateNumberOfPromotionalProducts(CartItem cartItem, Promotion promotion){
        return cartItem.getQuantity()/promotion.getBuy()*promotion.getGet();
    }

    private boolean isInPromotionPeriod(Promotion promotion){
        LocalDateTime today = DateTimes.now();
        return (today.isEqual(promotion.getStartDate()) || today.isAfter(promotion.getStartDate())) && (today.isEqual(promotion.getEndDate()) || today.isBefore(promotion.getEndDate()) || today.isBefore(promotion.getEndDate()));
    }

    private Promotion isPromotion(Product product){
        if(product.getPromotionName() != null){
            return findByName(product.getPromotionName());
        }
        return null;
    }

    private Promotion findByName(String name){
        for (Promotion promotion : Promotion.values()) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }
        return null;
    }
}
