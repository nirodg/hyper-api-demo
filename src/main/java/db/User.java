package db;

import db.enums.UserRole;
import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import dev.hyperapi.runtime.core.processor.annotations.Mapping;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@HyperResource(
    path = "/users",
    repositoryPackage = "repositories",
    mapping = @Mapping(
        ignore = {"createdBy", "updatedBy", "createdOn", "updatedOn"}
    ),
    events = @Events(onCreate = true, onUpdate = true, onDelete = true, onPatch = true )
)
public class User extends HyperEntity {

    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Order> orders;
}
