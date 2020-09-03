
//Namespace
package com.ecommerce.sms.controller.stock;


//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.factory.ObjectFactory;
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
public class CreateProductStockEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/stock";

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void savePromotionOkTest() {
        //Create payload
        ProductStock productStockPayload = ObjectFactory.generateSampleProductStock();

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(productStockPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<ProductStock> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().getProductId() < 0);
        Assertions.assertTrue(response.getBody().getQuantity() > 0);
    }

    @Test
    void wrongPayload() {
        //Create payload
        ProductStock productStockPayload = ObjectFactory.generateSampleProductStock();
        productStockPayload.setQuantity(-1);

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(productStockPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<ProductStock> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
