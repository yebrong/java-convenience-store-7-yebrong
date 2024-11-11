package store.view;

import store.order.domain.*;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String CUT_LINE_OF_STORE = "===========W 편의점=============";
    private static final String CART_CATEGORY = "상품명\t\t수량\t금액\n";
    private static final String CART_LIST = "%s\t\t%d \t%d\n";
    private static final String CUT_LINE_OF_PROMOTIONAL_PRODUCT = "===========증\t정=============";
    private static final String PROMOTIONAL_PRODUCT_LIST = "%s\t\t%d\n";
    private static final String CUT_LINE_OF_PAYMENT = "==============================";
    private static final String PAYMENT_LIST = "총구매액\t%s\n행사할인\t-%s\n멤버십할인\t-%s\n내실돈\t%s\n";



    public static void printReceipt(Order order, Payment payment) {
        printCutLine(CUT_LINE_OF_STORE);
        printCutLine(CART_CATEGORY);
        printCart(CART_LIST,order.getCart());
        printCutLine(CUT_LINE_OF_PROMOTIONAL_PRODUCT);
        printPromotionalProductList(PROMOTIONAL_PRODUCT_LIST,order.getPromotionalProductList());
        printCutLine(CUT_LINE_OF_PAYMENT);
        printPayment(payment);
    }
    public static String formattedNumber (int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
    private static void printPayment(Payment payment) {
        System.out.printf(PAYMENT_LIST,
                formattedNumber(payment.getTotalAmount()),
                formattedNumber(payment.getPromotionDiscountAmount()),
                formattedNumber(payment.getMembershipDiscountAmount()),
                formattedNumber(payment.getFinalAmountDue()));
    }
    private static void printPromotionalProductList (String message, List<PromotionalProduct> promotionalProductList) {
        for(PromotionalProduct promotionalProduct : promotionalProductList) {
            printPromotionalProduct(message,promotionalProduct);
        }
    }
    private static void printPromotionalProduct (String message, PromotionalProduct promotionalProduct){
        System.out.printf(message, promotionalProduct.getProductName(),
                promotionalProduct.getQuantity());
    }
    private static void printCart(String message, Cart cart) {
        for(CartItem cartItem : cart.getCartItemList()){
            printCartItem(message, cartItem);
        }
    }
    private static void printCartItem(String message, CartItem cartItem) {
        System.out.printf(message,
                cartItem.getProduct().getName(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice()*cartItem.getQuantity());
    }
    private static void printCutLine(String message){
        System.out.println(message);
    }

}
