package db.events;

import db.Order;
import db.service.ProductService;
import dev.hyperapi.runtime.core.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class GenericEvents {

    @Inject
    ProductService productService;

    public void onEntityEvent(@Observes EntityEvent<?> event) {
        System.out.println("Observed event type: " + event);
    }
    public void onOrderEvent(@Observes EntityEvent<Order> event) {
        System.out.println("[ORDER!!] Observed event type: " + event);

    }
}
