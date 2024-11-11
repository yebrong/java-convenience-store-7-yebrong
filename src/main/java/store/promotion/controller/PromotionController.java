package store.promotion.controller;

import store.order.domain.CartItem;
import store.product.domain.Product;
import store.promotion.domain.Promotion;
import store.promotion.service.PromotionService;
import store.view.InputView;


public class PromotionController {
    private final PromotionService promotionService;
    public PromotionController() {
        this.promotionService = new PromotionService();
    }

    public Integer run(CartItem cartItem) {
        Promotion promotion = promotionService.checkPromotion(cartItem.getProduct());
        int count = cartItem.isPromotion() ? promotionService.calculateNumberOfPromotionalProducts(cartItem, promotion) : 0;

        if (count > 0 && "Y".equals(InputView.infoPromotion(cartItem.getProduct(), count))) {
            if (promotionService.addFreeProduct(cartItem, promotion)) {
                return count; // 멤버십 여부로 넘어가도록 여기서 바로 반환
            }
        }

        return 0;
    }
}
