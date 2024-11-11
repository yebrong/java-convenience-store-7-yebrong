package store.order.domain;

import store.product.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart(){
        this.cartItemList = new ArrayList<>();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public String getCartItemListToString(){
        return cartItemList.toString();
    }

}
