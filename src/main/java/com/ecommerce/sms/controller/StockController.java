
//Namespace
package com.ecommerce.sms.controller;

//Imports
import com.ecommerce.sms.domain.api.ApiErrorResponse;
import com.ecommerce.sms.domain.api.ApiMessageResponse;
import com.ecommerce.sms.domain.model.ProductStock;
import com.ecommerce.sms.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Endpoint for Stock
 */
@Tag(name = "STOCK", description = "Endpoint for stock operations")
@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    //Fields
    @Autowired
    private transient StockService stockService;

    /**
     * Fetch products stock
     * @return
     */
    @Operation(summary = "Fetch products stock", description = "Retrieve stock of products", tags = { "stock" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductStock.class)))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ProductStock>> fetchStockOfProducts() {

        //Retrieve productsStock
        Iterable<ProductStock> stockOfProducts = stockService.fechProductsAvailability();

        //Return the productsStock
        return new ResponseEntity<>(stockOfProducts, HttpStatus.OK);
    }



    /**
     * Create stock
     * @return
     */
    @Operation(summary = "Create a new product stock", description = "New product stock", tags = { "stock" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resource created", content = @Content(schema = @Schema(implementation = ProductStock.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductStock> createProductStock(@RequestBody @Valid ProductStock productStock) {

        //Create stock
        ProductStock stockCreated = stockService.save(productStock);

        //Return the stock created
        return new ResponseEntity<>(stockCreated, HttpStatus.CREATED);
    }


    /**
     * Update stock
     * @return
     */
    @Operation(summary = "Update product stock", description = "Update product stock", tags = { "stock" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProductStock.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductStock> updateStock(@PathVariable @Valid Long id, @RequestBody @Valid ProductStock stock) {

        //Assign Id
        stock.setProductId(id);

        //Update stock
        ProductStock stockUpdated = stockService.save(stock);

        //Return the stock ypdated
        return new ResponseEntity<>(stockUpdated, HttpStatus.OK);
    }


    /**
     * Delete stock
     * @return
     */
    @Operation(summary = "Delete a stock", description = "Delete a stock", tags = { "stock" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ApiMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Malformed request syntax", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "The service encountered a problem.", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiMessageResponse> deleteStock(@PathVariable @Valid Long id) {

        //Delete stock
        stockService.delete(id);

        //Return the message
        return new ResponseEntity<>(new ApiMessageResponse(HttpStatus.OK, "Stock Deleted"), HttpStatus.OK);
    }


    
}
