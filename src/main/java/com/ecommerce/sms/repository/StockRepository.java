
//Namespace
package com.ecommerce.sms.repository;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends CrudRepository<ProductStock, Long> {
}
