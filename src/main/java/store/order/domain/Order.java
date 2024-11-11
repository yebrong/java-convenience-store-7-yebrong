package store.order.domain;

import java.util.List;

public class Order {
    private Cart cart;
    private List<PromotionalProduct> promotionalProductList;

    public Order(Cart cart) {
        this.cart = cart;
    }

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
