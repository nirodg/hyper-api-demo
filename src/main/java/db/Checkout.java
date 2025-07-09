package db;


import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.RestService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@RestService(
        path = "/checkouts",
        dto = "CheckoutDto",
        scope = RestService.Scope.REQUEST,
        pageable = @RestService.Pageable(
                limit = 33,
                maxLimit = 500
        ),
        disabledFor = {RestService.HttpMethod.GET},
        events = @RestService.Events(
                onCreate = true)
)
public class Checkout extends HyperEntity {

    @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
