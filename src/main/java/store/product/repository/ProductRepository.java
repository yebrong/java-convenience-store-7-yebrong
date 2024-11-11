package store.product.repository;

import store.product.domain.Product;

import java.util.List;

public class ProductRepository {


    public Product findByName(List<Product> products, String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public boolean isStockAvailable(Product product, int quantity) {
        return product.getStock() >= quantity;
    }

    public Product findNonPromotionByProduct(List<Product> products, Product product){
        for(Product productItem : products){
            if(productItem.getName().equals(product.getName()) && productItem.getPromotionName() ==null){
                return productItem;
            }
        }
        return null;
    }



}
