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
        Product product = cartItem.getProduct();
        Promotion promotion = promotionService.checkPromotion(product);
        int count = promotionService.calculateNumberOfPromotionalProducts(cartItem, promotion);

        // 프로모션이 적용될 수 있는 경우
        if (count != 0 && cartItem.isPromotion()) {
            String answer = InputView.infoPromotion(product, count);

            // 'Y'가 눌렸을 때, 무료 상품을 추가하고 프로모션 혜택 적용
            if ("Y".equals(answer)) {
                if (promotionService.addFreeProduct(cartItem, promotion)) {
                    return count; // 무료 상품이 추가되었으면 그 개수를 반환
                } else {
                    InputView.paymentWithoutBenefits(cartItem); // 혜택 없이 결제할 경우 처리
                    return 0;
                }
            }

            // 'N'이 눌렸을 때 프로모션을 적용하지 않겠다는 선택
            if ("N".equals(answer)) {
                return 0;
            }
        }

        // 프로모션 혜택이 적용되지 않거나, 기타 조건일 경우
        InputView.paymentWithoutBenefits(cartItem);
        return 0;
    }
}
