
//Namespace
package com.ecommerce.sms.service;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.factory.ObjectFactory;
import com.ecommerce.sms.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockServiceTest {

    //Fields
    @Autowired
    private transient StockService stockService;

    @Autowired
    private transient StockRepository stockRepository;

    @Test
    void testDbOperations() {

        //Insert
        long rowsBefore = stockRepository.count();
        ProductStock productStock = stockService.save(ObjectFactory.generateSampleProductStock());
        long rowsAfter = stockRepository.count();
        Assertions.assertEquals(rowsBefore + 1, rowsAfter);

        //Fetch
        List<ProductStock> products = (List<ProductStock>) stockService.fechProductsAvailability();
        Assertions.assertEquals(rowsAfter, products.size());

        //Find specific product
        ProductStock productStockSearched = stockService.findProductAvailability(productStock.getProductId());
        Assertions.assertEquals(productStock.getProductId(), productStockSearched.getProductId());
        Assertions.assertEquals(productStock.getQuantity(), productStockSearched.getQuantity());

        //Delete
        stockService.delete(productStockSearched.getProductId());
        Assertions.assertEquals(rowsBefore, stockRepository.count());
    }


}
