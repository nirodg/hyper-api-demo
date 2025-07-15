package events;

import db.Order;
import db.User;
import com.eorghe.hyperapi.events.EntityEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class GenericEvents {

    public void onEntityEvent(@Observes EntityEvent<?> event) {
        System.out.println("Observed event type: " + event);
    }
    public void onOrderEvent(@Observes EntityEvent<Order> event) {
        System.out.println("[ORDER!!] Observed event type: " + event);
    }

    public void onUsersEvent(@Observes EntityEvent<User> event) {
        sendActivationEmail(event.getEntity());
    }

    // Simulates sending an email to the user
    void sendActivationEmail(User user) {
        System.out.println("Sending email to user: " + user.getEmail());
    }
}
