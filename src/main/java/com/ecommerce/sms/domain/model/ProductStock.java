
//Namespace
package com.ecommerce.sms.domain.model;

//Imports
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Class that represents and persist product stock entity
 */
@Entity(name = "product_stock")
public class ProductStock {

    //Fields
    @Id
    @Column(name = "product")
    private Long productId;

    @Min(value = 0)
    @NotNull
    private Integer quantity;


    //Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
