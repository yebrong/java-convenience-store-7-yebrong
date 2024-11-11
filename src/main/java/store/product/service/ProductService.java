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

    // 상품의 구매 재고 차감
    // 상품의 프로모션 변경
    // 상품의 무료 재고 차감(프로모션 소유 여부, 프로모션 적용 횟수)
    // 프로모션 상품 차감


    // 최신 정보를 담고 있는 상품 목록 가져오기

    // 새로운 제품 등록
    // 상품의 가격 변경
    public void deductProductQuantity(Product product, int count){
        if(product.getStock()<count && product.getStock()>0){
            System.out.println("수량이 조금 부족하시네염");
            product.reduce(product.getStock());
            count -= product.getStock();
            Product nonPromotionProduct = productRepository.findNonPromotionByProduct(products, product);
            nonPromotionProduct.reduce(count);
        }

        if(product.getStock()>count){
            System.out.println("수량이 충분하시네염");
            product.reduce(count);
        }

        if(product.getStock()==0){
            System.out.println("수량이 없으시네염");
            Product nonPromotionProduct = productRepository.findNonPromotionByProduct(products, product);
            nonPromotionProduct.reduce(count);
        }
    }


}
