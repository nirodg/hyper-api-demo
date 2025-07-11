package db;

import db.events.KafkaEvents;
import dev.hyperapi.runtime.core.model.BaseEntity;
import dev.hyperapi.runtime.core.processor.annotations.RestService;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@RestService(
        path = "/prods",
        dto = "MyProduct",
        repositoryPackage = "repositories",
        scope = RestService.Scope.REQUEST,
        mapping = @RestService.Mapping(
                ignoreNested = {"orders.checkout.orders"}
        ),

//        disabledFor = {RestService.HttpMethod.POST, RestService.HttpMethod.POST, RestService.HttpMethod.PATCH},
        events = @RestService.Events(
                onCreate = true,
                onDelete = true,
                onPatch = true,
                onUpdate = true,
                emitter = KafkaEvents.class
        )

)
public class Product extends BaseEntity {

    String name;
    BigDecimal price;

    @JsonbTransient
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
