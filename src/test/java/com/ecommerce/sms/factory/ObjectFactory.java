
//Namespace
package com.ecommerce.sms.factory;

//Imports
import com.ecommerce.sms.domain.model.ProductStock;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FActory of Objects
 */
public class ObjectFactory {

    public static ProductStock generateSampleProductStock() {

        ProductStock productStock = new ProductStock();
        productStock.setProductId(Long.parseLong(RandomStringUtils.randomNumeric(6)) * -1);
        productStock.setQuantity(Integer.valueOf(RandomStringUtils.randomNumeric(1, 2)));
        return productStock;
    }


}
