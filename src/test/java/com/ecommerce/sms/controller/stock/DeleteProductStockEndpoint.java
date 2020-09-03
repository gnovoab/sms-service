
//Namespace
package com.ecommerce.sms.controller.stock;

//Imports

import com.ecommerce.sms.domain.api.ApiMessageResponse;
import com.ecommerce.sms.domain.model.ProductStock;
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

import java.util.Optional;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteProductStockEndpoint {

    //Fields
    private static final String BASE_URL = "/api/v1/stock";

    @Autowired
    private transient StockRepository stockRepository;

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void deletePromotionOk() {
        //Prepopulate DB
        ProductStock originalProductStock = stockRepository.save(ObjectFactory.generateSampleProductStock());

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<ProductStock> response = restTemplate
                .exchange(BASE_URL + "/products/" + originalProductStock.getProductId(), HttpMethod.DELETE, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Optional<ProductStock> deletedProductStock =  stockRepository.findById(originalProductStock.getProductId());
        Assertions.assertFalse(deletedProductStock.isPresent());
    }


    @Test
    void deletePromotionWrongPayload() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Object> response = restTemplate
                .exchange(BASE_URL + "/products/a", HttpMethod.DELETE, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void deletePromotionWrongProduct() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<ApiMessageResponse> response = restTemplate
                .exchange(BASE_URL + "/products/-9839829", HttpMethod.DELETE, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
