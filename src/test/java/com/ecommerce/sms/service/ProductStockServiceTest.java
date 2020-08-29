
//Namespace
package com.ecommerce.sms.service;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.factory.ObjectFactory;
import com.ecommerce.sms.repository.ProductStockRepository;
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
public class ProductStockServiceTest {

    //Fields
    @Autowired
    private transient ProductStockService productStockService;

    @Autowired
    private transient ProductStockRepository productStockRepository;

    @Test
    void testDbOperations() {

        //Insert
        long rowsBefore = productStockRepository.count();
        ProductStock productStock = productStockService.save(ObjectFactory.generateSampleProductStock());
        long rowsAfter = productStockRepository.count();
        Assertions.assertEquals(rowsBefore + 1, rowsAfter);

        //Fetch
        List<ProductStock> products = (List<ProductStock>) productStockService.fechProductsAvailability();
        Assertions.assertEquals(rowsAfter, products.size());

        //Find specific product
        ProductStock productStockSearched = productStockService.findProductAvailability(productStock.getProductId());
        Assertions.assertEquals(productStock.getProductId(), productStockSearched.getProductId());
        Assertions.assertEquals(productStock.getQuantity(), productStockSearched.getQuantity());

        //Delete
        productStockService.delete(productStockSearched.getProductId());
        Assertions.assertEquals(rowsBefore, productStockRepository.count());
    }


}
