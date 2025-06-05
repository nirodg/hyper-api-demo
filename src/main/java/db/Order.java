package db;

import dev.hyperapi.runtime.core.model.BaseEntity;
import dev.hyperapi.runtime.core.processor.annotations.RestService;
import jakarta.persistence.Entity;

@Entity
@RestService(
        mapping = @RestService.Mapping(ignore = {"internalId"})
)
public class Order extends BaseEntity {

    int orderNumber;
    int stockQuantity;
    String internalId;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }
}
