package store.product.service;

import store.common.exception.StoreException;
import store.product.domain.Product;
import store.product.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    private List<Product> products;

    public ProductService(List<Product> products){
        productRepository = new ProductRepository();
        this.products = products;
    }

    public Product findByNameFromList(String productName) {
        if(productRepository.findByName(products, productName) == null) {
            throw new IllegalArgumentException(StoreException.NON_EXISTING_PRODUCT.getMessage());
        }
        return productRepository.findByName(products, productName);
    }

    public boolean isQuantitySufficient(Product product, int quantity) {
        if(!productRepository.isStockAvailable(product, quantity)){
            throw new IllegalArgumentException(StoreException.EXCEEDING_PURCHASE_QUANTITY.getMessage());
        }
       return productRepository.isStockAvailable(product, quantity);
    }

    public void deductProductQuantity(Product product, int count){
        if(product.getStock()<count && product.getStock()>0){
            reduceProductStockAndNonPromotionStock(product, count);
        }
        if(product.getStock()>count){
            product.reduce(count);
        }
        if(product.getStock()==0){
            Product nonPromotionProduct = productRepository.findNonPromotionByProduct(products, product);
            nonPromotionProduct.reduce(count);
        }
    }

    private void reduceProductStockAndNonPromotionStock(Product product, int count){
        product.reduce(product.getStock());
        count -= product.getStock();
        Product nonPromotionProduct = productRepository.findNonPromotionByProduct(products, product);
        nonPromotionProduct.reduce(count);
    }
}
