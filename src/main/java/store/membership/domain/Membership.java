package store.membership.domain;

import store.order.domain.Cart;

public enum Membership {
    NONE("N"), APPLIED("Y");
    public String membershipAnswer;
    public static final int MAXIMUM_DISCOUNT_AMOUNT = 8000;
    public static final double DISCOUNT_RATE = 0.3;

    Membership(String membershipAnswer) {
        this.membershipAnswer = membershipAnswer;
    }

    public String getMembershipAnswer() {
        return membershipAnswer;
    }

    // 카트의 카트 리스트를 순환하면서 isPromotion이 아닌 cartItem들만 모아서 반환
    // 해당 cartItem에 담긴 것들의 총 금액을 환산하여
    // 30% 적용 시 할인 금액을 반환하고
    // 해당 금액이 8000원이 넘으면 8000원만 할인 적용
    // 그렇지 않으면 할인 금액 적용해서 membership 반환
}
