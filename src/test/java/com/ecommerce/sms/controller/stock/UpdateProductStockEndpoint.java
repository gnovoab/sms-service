
//Namespace
package com.ecommerce.sms.controller.stock;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.exception.ResourceNotFoundException;
import com.ecommerce.sms.factory.ObjectFactory;
import com.ecommerce.sms.repository.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateProductStockEndpoint {

    //Fields
    private static final String BASE_URL = "/api/v1/stock";

    @Autowired
    private transient StockRepository stockRepository;

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void updateStockOk() {

        //Prepopulate DB
        ProductStock originalProductStock = stockRepository.save(ObjectFactory.generateSampleProductStock());

        //Create payload
        ProductStock productStockPayload = ObjectFactory.generateSampleProductStock();

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(productStockPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<ProductStock> response = restTemplate
                .exchange(BASE_URL + "/products/" + originalProductStock.getProductId(), HttpMethod.PUT, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        ProductStock updatedProductStock =  stockRepository
                .findById(originalProductStock.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Assertions.assertEquals(originalProductStock.getProductId(), updatedProductStock.getProductId());
        Assertions.assertNotEquals(originalProductStock.getQuantity(), updatedProductStock.getQuantity());
    }
}
