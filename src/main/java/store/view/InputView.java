package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.order.domain.CartItem;
import store.product.domain.Product;

import java.util.List;
import java.util.Optional;

import static store.view.OutputView.formattedNumber;

public class InputView {
    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.";
    private static final String REQUEST_PRODUCT_AND_QUANTITY_MESSAGE = "구매하실 상품명과 수령을 입력해 주세요. (예: [사이다-2], [감자칩-1])";
    private static final String CHECK_MEMBERSHIP_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String IS_MORE_PURCHASE_MESSAGE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? Y/N";
    private static final String NO_DISCOUNT_WARNING = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n";
    private static final String FREE_PROMOTION_OFFER = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n";
    private static final String OUT_OF_STOCK = "재고 없음";

    public static String inputMembershipCheck() {
        System.out.println(CHECK_MEMBERSHIP_MESSAGE);
        return Console.readLine().strip();
    }

    public static String welcomeMessage(List<Product> list){
        System.out.println(WELCOME_MESSAGE);
        printLine();
        for(Product product : list){
            customProduct(product);
        }
        return inputString(REQUEST_PRODUCT_AND_QUANTITY_MESSAGE);
    }

    public static String inputProductName() {
        return Console.readLine().strip();
    }

    public static String inputQuantity() {
        return Console.readLine().strip();
    }

    private static void printLine(){
        System.out.println();
    }

    private static String inputString(String message){
        System.out.println(message);
        return Console.readLine().strip();
    }

    private static void customProduct(Product product) {
        String stock = customStock(product);
        String formattedStock = stock;
        if (!stock.equals(OUT_OF_STOCK)) {
            formattedStock = stock + "개";
        }

        System.out.printf("- %s %s원 %s %s\n",
                product.getName(),
                formattedNumber(product.getPrice()),
                formattedStock,
                Optional.ofNullable(product.getPromotionName()).orElse("")
        );
    }

    private static String customStock(Product product) {
        if (product.getStock() == 0) {
            return OUT_OF_STOCK;
        }
        return String.valueOf(product.getStock()).strip();
    }



    public static String infoPromotion(Product product, int count){
        System.out.printf(FREE_PROMOTION_OFFER,
                product.getName(),
                count);
        return Console.readLine().strip();
    }


    public static String paymentWithoutBenefits(CartItem cartItem){
        System.out.printf(NO_DISCOUNT_WARNING,
                cartItem.getName(),
                cartItem.getQuantity());
        return Console.readLine().strip();
    }

    public static String checkTryOrder(List<Product> productList) {
        System.out.println(IS_MORE_PURCHASE_MESSAGE);
        return Console.readLine().strip();
    }

}
