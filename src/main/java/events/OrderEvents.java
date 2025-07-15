package events;

import db.Order;
import com.eorghe.hyperapi.events.AbstractTypedEmitter;
import com.eorghe.hyperapi.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderEvents extends AbstractTypedEmitter<Order> {

    protected OrderEvents() {
        super(Order.class);
    }

    @Override
    protected void emitTyped(EntityEvent.Type type, Order entity) {
        switch (type){
            case CREATE -> postCreate(entity);
            case UPDATE -> postUpdate(entity);
            case DELETE -> postDelete(entity);
            case PATCH -> postPatch(entity);
        }
    }

    void postCreate(Order Order) {
        System.out.println("Post-create processing for Order: " + Order.getOrderNumber());
        // Add any additional logic needed after the event is emitted
    }

    void postUpdate(Order Order) {
        System.out.println("Post-update processing for Order: " + Order.getOrderNumber());
        // Add any additional logic needed after the event is emitted
    }

    void postDelete(Order Order) {
        System.out.println("Post-delete processing for deleted Order");
        // Add any additional logic needed after the event is emitted
    }

    void postPatch(Order Order) {
        System.out.println("Post-patch processing for Order: " + Order.getOrderNumber());
        // Add any additional logic needed after the event is emitted
    }
}
