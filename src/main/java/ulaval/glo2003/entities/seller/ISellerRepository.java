package ulaval.glo2003.entities.seller;

import java.util.List;
import java.util.UUID;

public interface ISellerRepository {
    Seller get(UUID id);

    Boolean exists(UUID id);

    void save(Seller entity);

    List<Seller> getAll();
}
