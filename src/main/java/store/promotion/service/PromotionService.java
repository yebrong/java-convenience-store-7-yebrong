package store.promotion.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.order.domain.CartItem;
import store.product.domain.Product;
import store.promotion.domain.Promotion;

import java.time.LocalDateTime;

public class PromotionService {

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
        if(promotion != null) {
            return cartItem.getQuantity()/promotion.getBuy()*promotion.getGet();
        }
        return 0;
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
