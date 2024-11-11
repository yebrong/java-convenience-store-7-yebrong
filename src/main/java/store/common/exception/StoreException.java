package store.common.exception;

public enum StoreException {
    NULL_INPUT("입력값이 존재하지 않습니다."),
    INVALID_ORDER_FORMAT("올바르지 않은 형식으로 입력했습니다. "),
    NON_EXISTING_PRODUCT("존재하지 않는 상품입니다. "),
    EXCEEDING_PURCHASE_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. "),
    OTHER_INCORRECTLY_ENTERED_CASES("잘못된 입력입니다. ");


    private final String ERROR_HEADER = "[ERROR] ";
    private final String PLEASE_INPUT_AGAIN = "다시 입력해 주세요.";
    private final String message;

    StoreException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_HEADER+message+PLEASE_INPUT_AGAIN;
    }
}
