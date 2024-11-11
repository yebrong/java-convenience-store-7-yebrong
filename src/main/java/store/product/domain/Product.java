package store.product.domain;

public class Product {
    private String name;
    private int price;
    private int stock;
    private String promotionName;

    private Product(){

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

    public void reduce(int count){
        stock -= count;
    }

}
