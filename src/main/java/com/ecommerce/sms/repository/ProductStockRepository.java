
//Namespace
package com.ecommerce.sms.repository;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import org.springframework.data.repository.CrudRepository;

public interface ProductStockRepository extends CrudRepository<ProductStock, Long> {
}
