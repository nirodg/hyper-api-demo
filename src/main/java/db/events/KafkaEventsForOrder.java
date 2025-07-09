package db.events;

import db.Order;
import dev.hyperapi.runtime.core.events.AbstractTypedEmitter;
import dev.hyperapi.runtime.core.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEventsForOrder extends AbstractTypedEmitter<Order> {

//    @Autowired
//    private KafkaTemplate<String, KafkaPayload> kafkaTemplate;

    protected KafkaEventsForOrder() {
        super(Order.class);
    }

    // Kafka
    @Override
    protected void emitTyped(EntityEvent.Type type, Order entity) {
        System.out.println(String.format("Emitting event: %s for entity: %s", type, entity.toString()));
        //kafkaTemplate.send(payload.getTopic().name(), payload);
    }
}