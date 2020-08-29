
//Namespace
package com.ecommerce.sms.service;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;

public interface ProductStockService {
    Iterable<ProductStock> fechProductsAvailability();
    ProductStock findProductAvailability(long id);
    ProductStock save(ProductStock productStock);
    void delete(long id);
}
