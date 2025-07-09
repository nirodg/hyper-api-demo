package db.events;

import db.Product;
import dev.hyperapi.runtime.core.events.AbstractTypedEmitter;
import dev.hyperapi.runtime.core.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvents extends AbstractTypedEmitter<Product> {

//    @Autowired
//    private KafkaTemplate<String, KafkaPayload> kafkaTemplate;

    protected KafkaEvents() {
        super(Product.class);
    }

    // Kafka
    @Override
    protected void emitTyped(EntityEvent.Type type, Product entity) {
        System.out.println(String.format("Emitting event: %s for entity: %s", type, entity.toString()));
        //kafkaTemplate.send(payload.getTopic().name(), payload);
    }
}
