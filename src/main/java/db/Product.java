package db;

import dev.hyperapi.runtime.core.model.BaseEntity;
import dev.hyperapi.runtime.core.processor.annotations.RestService;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@RestService(
        path = "/prods",
        dto = "ProductSingleDto",
        scope = RestService.Scope.REQUEST
)
public class Product extends BaseEntity {

    String name;
    BigDecimal price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
