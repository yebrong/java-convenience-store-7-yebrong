package store.order.domain;

import store.product.domain.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private boolean isPromotion;

    public CartItem(Product product, int quantity, boolean isPromotion) {
        this.product = product;
        this.quantity = quantity;
        this.isPromotion = isPromotion;
    }

    private CartItem(){
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public String getName() {
        return product.getName();
    }

    public Product getProduct() {
        return product;
    }
    public Integer getQuantity() {
        return quantity;
    }

}
