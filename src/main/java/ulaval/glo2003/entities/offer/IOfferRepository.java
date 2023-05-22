package ulaval.glo2003.entities.offer;

import java.util.List;
import java.util.UUID;

public interface IOfferRepository {
    Offer get(UUID id);

    Boolean exists(UUID id);

    void save(Offer entity);

    List<Offer> getAll();

    List<Offer> getOffersForProduct(UUID productId);
}
