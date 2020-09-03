
//Namespace
package com.ecommerce.sms.controller.stock;

//Imports
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

import java.util.List;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchStockEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/stock";

    @Autowired
    private transient TestRestTemplate restTemplate;
    
    @Autowired
    private transient StockRepository stockRepository;


    @Test
    void fetchStockTest() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<List<ProductStock>> response = restTemplate
                .exchange(BASE_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
}
