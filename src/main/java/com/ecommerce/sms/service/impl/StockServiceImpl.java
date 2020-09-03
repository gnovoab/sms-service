
//Namespace
package com.ecommerce.sms.service.impl;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.exception.ResourceNotFoundException;
import com.ecommerce.sms.repository.StockRepository;
import com.ecommerce.sms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Class hat handles operations regarding product stock availability
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private transient StockRepository stockRepository;

    @Override
    public Iterable<ProductStock> fechProductsAvailability() {
        return stockRepository.findAll();
    }

    @Override
    public ProductStock findProductAvailability(long id) {
        return stockRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public ProductStock save(ProductStock productStock) {
        return stockRepository.save(productStock);
    }

    @Override
    public void delete(long id) {
        try {
            stockRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

}
