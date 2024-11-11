package store.order.controller;

import store.common.utils.StoreStringTokenizer;
import store.order.domain.CartItem;
import java.util.Map;

public class CartController {

    // string을 입력받아서 Product, quantity로 cart에 추가하기
    public void addCart(String input){
        Map<String, Object> map = StoreStringTokenizer.tokenizerStringAndInteger(input);


    }
}
