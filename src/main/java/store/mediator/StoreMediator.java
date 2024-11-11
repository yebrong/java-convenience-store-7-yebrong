package store.mediator;

import store.common.exception.StoreException;
import store.common.utils.StoreStringTokenizer;
import store.order.domain.*;
import store.order.service.CartService;
import store.product.domain.Product;
import store.product.service.ProductService;
import store.promotion.controller.PromotionController;
import store.view.InputView;
import store.view.OutputView;

import java.util.*;

public class StoreMediator {
    private static final String filePath = "src/main/resources/products.md";
    private ProductService productService;
    private CartService cartService;
    private PromotionController promotionController;
    private List<Product> products;

    public StoreMediator(){
        products = storeInitialize();
        productService = new ProductService(products);
        cartService =  new CartService();
        promotionController = new PromotionController();
    }

    private String start(){
        Cart cart = new Cart();
        while (true) {
            try {
                requestProduct(cart);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
       return printReceiptResult(applyPromotionToCart(cart));
    }

    private void requestProduct(Cart cart) {
        String input = InputView.welcomeMessage(products);
        addCartItemList(cart, input);
    }
    private String printReceiptResult(Map<String, Object> result) {
        Order order = (Order) result.get("order");
        Payment payment = (Payment) result.get("payment");

        OutputView.printReceipt(order, payment);
        return InputView.checkTryOrder(products);
    }
    public void run(){
        String result = start();
        while (true) {
            if ("Y".equalsIgnoreCase(result)) {
                start();  // 장바구니 초기화
                continue;  // 반복문 계속
            }
            if ("N".equalsIgnoreCase(result)) {
                break;  // "N" 입력 시 종료
            }
        }
    }


    private List<Product> storeInitialize(){
        return StoreStringTokenizer.loadProductsFromFile(filePath);
    }

    private Map<String, Object> applyPromotionToCart(Cart cart) {
        Map<String, Object> result = new HashMap<>();
        List<PromotionalProduct> promotionalProductList = processPromotions(cart);

        Order order = createOrder(cart, promotionalProductList);
        Payment payment = createPayment(cart, promotionalProductList);

        result.put("order", order);
        result.put("payment", payment);
        return result;
    }

    private List<PromotionalProduct> processPromotions(Cart cart) {
        List<PromotionalProduct> promotionalProductList = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.isPromotion()) {
                promotionalProductList.add(applyPromotion(cartItem));
            } else {
                handleNonPromotionalItem(cartItem);
            }
        }
        return promotionalProductList;
    }

    private PromotionalProduct applyPromotion(CartItem cartItem) {
        int promotionCount = promotionApplication(cartItem);
        return addPromotionToCart(cartItem.getProduct(), promotionCount);
    }

    private void handleNonPromotionalItem(CartItem cartItem) {
        promotionNonApplication(cartItem);
    }

    private Order createOrder(Cart cart, List<PromotionalProduct> promotionalProductList) {
        return new Order(cart, promotionalProductList);
    }

    private Payment createPayment(Cart cart, List<PromotionalProduct> promotionalProductList) {
        String checkMembership = InputView.inputMembershipCheck();
        return new Payment(cart, promotionalProductList, checkMembership);
    }



    private void addCartItemList(Cart cart, String input){
        List<Map<String,Object>> mapList = StoreStringTokenizer.getListOfPurchaseItemList(input);
        for(Map<String,Object> map : mapList){
            CartItem cartItem = parseStringToCartItem(map);
            cartService.addCart(cart,cartItem);
        }
    }

    private CartItem parseStringToCartItem(Map<String, Object> map) {
        Object productNameObject = map.get("stringData");
        Object quantityObject = map.get("intData");
        Product product = inputProductName(productNameObject);
        int quantity = inputQuantity(product, quantityObject);
        return new CartItem(product, quantity, true);
    }

    private Integer inputQuantity(Product product, Object quantityObject) {
        System.out.println("5");
        try {
            int quantity = Integer.parseInt(quantityObject.toString());
            if(productService.isQuantitySufficient(product,quantity)){
                return quantity;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    private Product inputProductName(Object productNameObject) {
        System.out.println("6");
        try {
            return productService.findByNameFromList(productNameObject.toString());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Integer promotionApplication(CartItem cartItem) {
        Product product = cartItem.getProduct();
        int promotionCount = promotionController.run(cartItem);
        product.reduce(cartItem.getQuantity());
        productService.deductProductQuantity(product,promotionCount);
        return promotionCount;

    }

    private PromotionalProduct addPromotionToCart(Product product, int promotionCount) {
        return new PromotionalProduct(product, promotionCount);
    }

    private void promotionNonApplication(CartItem cartItem) {
        Product product = cartItem.getProduct();
        if (product.getStock() >= cartItem.getQuantity()) {
            product.reduce(cartItem.getQuantity());
        }

    }
}
