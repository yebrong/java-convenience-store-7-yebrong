package store.product.domain;

public class Product {
    private String name;
    private int price;
    private int stock;
    private String promotionName;

    private Product(){

    }

    public boolean isPromotionApplied() {
        return promotionName != null;
    }

    public Product(String name, int price, int stock, String promotionName) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotionName = promotionName;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getPromotionName() {
        return promotionName;
    }


    // 상품의 구매 재고 차감
    public void reduce(int count){
            stock -= count;
    }

    @Override
    public String toString(){
        return name+" "+price+" "+stock+" "+promotionName;
    }


    // 최신 정보를 담고 있는 상품 목록 가져오기




}
