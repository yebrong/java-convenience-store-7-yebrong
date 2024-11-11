package store.order.service;

import store.order.domain.Cart;
import store.order.domain.CartItem;

public class CartService {

    // 카트에 상품 추가
    public void addCart(Cart cart, CartItem cartItem) {
        cart.getCartItemList().add(cartItem);
    }
}
