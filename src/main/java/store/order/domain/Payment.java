package store.order.domain;

import store.membership.domain.Membership;

import java.util.List;
import static store.membership.domain.Membership.DISCOUNT_RATE;
import static store.membership.domain.Membership.MAXIMUM_DISCOUNT_AMOUNT;

public class Payment {
    private Integer totalAmount;
    private Integer promotionDiscountAmount = 0;
    private Integer membershipDiscountAmount;
    private Integer finalAmountDue;

    private Payment() {
    }

    public Payment(Cart cart, List<PromotionalProduct> promotionalProductList, String checkMembership) {
        this.totalAmount = calculateTotalAmount(cart);
        calculatePromotionDiscountAmount(promotionalProductList);
        calculateMembershipDiscountAmount(cart, checkMembership);
        this.finalAmountDue = totalAmount - promotionDiscountAmount - membershipDiscountAmount;
    }

    public Payment(Integer totalAmount, Integer promotionDiscountAmount, Integer membershipDiscountAmount) {
        this.totalAmount = totalAmount;
        this.promotionDiscountAmount = promotionDiscountAmount;
        this.membershipDiscountAmount = membershipDiscountAmount;
        this.finalAmountDue = totalAmount - promotionDiscountAmount - membershipDiscountAmount;
    }
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getPromotionDiscountAmount() {
        return promotionDiscountAmount;
    }

    public Integer getMembershipDiscountAmount() {
        return membershipDiscountAmount;
    }

    public Integer getFinalAmountDue() {
        return finalAmountDue;
    }

        private Integer calculateTotalAmount (Cart cart) {
        int totalAmount = 0;
        for (CartItem cartItem : cart.getCartItemList()) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalAmount;
    }

    private void calculatePromotionDiscountAmount (List<PromotionalProduct> promotionalProductList) {
        if (promotionalProductList != null) {  // Null 체크 추가
            for (PromotionalProduct promotionalProduct : promotionalProductList) {
                promotionDiscountAmount += promotionalProduct.getPrice() * promotionalProduct.getQuantity();
            }
        }
    }

    private void calculateMembershipDiscountAmount (Cart cart, String checkMembership) {
        int nonPromotionalProductAmount = separateNonPromotionalProduct(cart);
        if(checkMembership.equals(Membership.APPLIED.getMembershipAnswer())){
            applyMembership(nonPromotionalProductAmount);
        }
        if(checkMembership.equals(Membership.NONE.getMembershipAnswer())){
            membershipDiscountAmount = 0;
        }

    }

    private void applyMembership(int nonPromotionalProductAmount) {
        int afterPrice = (int) (nonPromotionalProductAmount * DISCOUNT_RATE);
        if (afterPrice > 8000) {
            membershipDiscountAmount = (int)MAXIMUM_DISCOUNT_AMOUNT;
        }
        if(afterPrice <= 8000) {
            membershipDiscountAmount = afterPrice;
        }
    }

    private Integer separateNonPromotionalProduct(Cart cart) {
        int nonPromotionalProductAmount = 0;
        for (CartItem cartItem : cart.getCartItemList()) {
            if(!cartItem.isPromotion()){
                nonPromotionalProductAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            }
        }
        return nonPromotionalProductAmount;
    }

}
