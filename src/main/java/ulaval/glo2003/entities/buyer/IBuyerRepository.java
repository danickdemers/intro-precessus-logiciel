package ulaval.glo2003.entities.buyer;

import java.util.List;
import java.util.UUID;

public interface IBuyerRepository {
    Buyer get(UUID id);

    Boolean exists(UUID id);

    void save(Buyer entity);

    List<Buyer> getAll();
}
