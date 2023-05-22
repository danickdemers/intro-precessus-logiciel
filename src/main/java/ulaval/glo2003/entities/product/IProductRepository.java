package ulaval.glo2003.entities.product;

import java.util.List;
import java.util.UUID;

public interface IProductRepository {
    Product get(UUID id);

    Boolean exists(UUID id);

    void save(Product entity);

    List<Product> getAll();
}
