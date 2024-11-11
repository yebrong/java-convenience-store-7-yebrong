package store.order.domain;

import store.product.domain.Product;

public class PromotionalProduct {
    private Product product;
    private Integer quantity;

    private PromotionalProduct() {

    }

    public PromotionalProduct(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public Integer getPrice() {
        return product.getPrice();
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return product.getName()+", "+quantity;
    }
}
