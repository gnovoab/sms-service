
//Namespace
package com.ecommerce.sms.service.impl;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.exception.ResourceNotFoundException;
import com.ecommerce.sms.repository.ProductStockRepository;
import com.ecommerce.sms.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Class hat handles operations regarding product stock availability
 */
@Service
@Transactional
public class ProductStockServiceImpl implements ProductStockService {

    @Autowired
    private transient ProductStockRepository productStockRepository;

    @Override
    public Iterable<ProductStock> fechProductsAvailability() {
        return productStockRepository.findAll();
    }

    @Override
    public ProductStock findProductAvailability(long id) {
        return productStockRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStock not found"));
    }

    @Override
    public ProductStock save(ProductStock productStock) {
        return productStockRepository.save(productStock);
    }

    @Override
    public void delete(long id) {
        productStockRepository.deleteById(id);
    }

}
