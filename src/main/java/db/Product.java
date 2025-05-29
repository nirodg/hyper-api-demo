/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import dev.hyperapi.runtime.annotations.ExposeAPI;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@ExposeAPI(
        path = "/products",
        mapping = @ExposeAPI.Mapping(ignore = {"id"}),
        pageable = @ExposeAPI.Pageable(limit = 50, maxLimit = 200),
        security = @ExposeAPI.Security(requireAuth = false),
        events = @ExposeAPI.Events(onCreate = true, onUpdate = true, onDelete = false),
        cache = @ExposeAPI.Cache(enabled = true, ttlSeconds = 300)
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    BigDecimal price;

}
