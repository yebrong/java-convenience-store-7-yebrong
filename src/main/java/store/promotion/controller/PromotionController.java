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

    public Integer run(CartItem cartItem){
        Product product = cartItem.getProduct();
        int quantity = cartItem.getQuantity();
        Promotion promotion = promotionService.checkPromotion(product);
    int count = promotionService.calculateNumberOfPromotionalProducts(cartItem, promotion);
        if(count != 0 && cartItem.isPromotion()){
            String answer = InputView.infoPromotion(product, count);
            if(answer.equals("Y")){
                if(!promotionService.addFreeProduct(cartItem, promotion)){
                    InputView.paymentWithoutBenefits(cartItem);
                    return 0;
                }
                return count;
            }
            if(answer.equals("N")){
                return 0;
            }
        }
        return 0;
    }
}
