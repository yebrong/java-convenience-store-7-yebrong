package store.order.domain;

import java.util.List;

public class Order {
    private Cart cart;
    private List<PromotionalProduct> promotionalProductList;

    public Order(Cart cart) {
        this.cart = cart;
    }

//    public Order(Cart cart, boolean isMembership) {
//        this.cart = cart;
//        int totalAmount = calculateTotalAmount(cart);
//        if(isMembership) {
//            Membership membership = new Membership(cart);
//            this.payment = new Payment(membership);
//        }
//        if(!isMembership) {
//            this.payment = new Payment();
//        }
//    }

    public Order(Cart cart, List<PromotionalProduct> promotionalProductList) {
        this.cart = cart;
        this.promotionalProductList = promotionalProductList;
    }

    public Cart getCart() {
        return cart;
    }


    public List<PromotionalProduct> getPromotionalProductList() {
        return promotionalProductList;
    }


    @Override
    public String toString() {
        return cart.toString()+","+promotionalProductList.toString();
    }

}
