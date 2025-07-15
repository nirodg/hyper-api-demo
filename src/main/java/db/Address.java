package db;

import com.eorghe.hyperapi.model.HyperEntity;
import com.eorghe.hyperapi.processor.annotations.Events;
import com.eorghe.hyperapi.processor.annotations.HyperResource;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "addresses")
@HyperResource(
    path = "/addresses",
    repositoryPackage = "repositories",
    events = @Events(onCreate = true, onUpdate = true, onDelete = true)
)
public class Address extends HyperEntity {

  private String street;
  private String city;
  private String zipCode;
  private String country;
  private boolean billing;
  private boolean shipping;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}