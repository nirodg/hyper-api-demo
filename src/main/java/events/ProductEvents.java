package events;

import db.Product;
import dev.hyperapi.runtime.core.events.AbstractTypedEmitter;
import dev.hyperapi.runtime.core.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductEvents extends AbstractTypedEmitter<Product> {

    protected ProductEvents() {
        super(Product.class);
    }

    @Override
    protected void emitTyped(EntityEvent.Type type, Product entity) {
        switch (type){
            case CREATE -> postCreate(entity);
            case UPDATE -> postUpdate(entity);
            case DELETE -> postDelete(entity);
            case PATCH -> postPatch(entity);
        }
    }

    void postCreate(Product product) {
        System.out.println("Post-create processing for product: " + product.getName());
        // Add any additional logic needed after the event is emitted
    }

    void postUpdate(Product product) {
        System.out.println("Post-update processing for product: " + product.getName());
        // Add any additional logic needed after the event is emitted
    }

    void postDelete(Product product) {
        System.out.println("Post-delete processing for deleted product");
        // Add any additional logic needed after the event is emitted
    }

    void postPatch(Product product) {
        System.out.println("Post-patch processing for product: " + product.getName());
        // Add any additional logic needed after the event is emitted
    }
}
