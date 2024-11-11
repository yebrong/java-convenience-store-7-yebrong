package store.order.service;

import store.common.utils.StoreStringTokenizer;
import store.order.domain.Cart;
import store.order.domain.CartItem;

public class CartService {

    // 카트에 상품 추가
    public void addCart(Cart cart, CartItem cartItem) {
        cart.getCartItemList().add(cartItem);
    }
}


// 카트에 상품 추가하면 프로모션 여부에 따라 프로모션 안내
