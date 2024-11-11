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
}
