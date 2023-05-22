package ulaval.glo2003.infrastructure.persistence.repository;

import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.application.exceptions.ItemNotFoundException;
import ulaval.glo2003.entities.buyer.Buyer;
import ulaval.glo2003.entities.buyer.IBuyerRepository;
import ulaval.glo2003.infrastructure.persistence.DbConnection;
import ulaval.glo2003.infrastructure.persistence.assemblers.BuyerDbAssembler;
import ulaval.glo2003.infrastructure.persistence.models.BuyerModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuyerRepository implements IBuyerRepository {
    private final DbConnection dbConnection;

    public BuyerRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Buyer get(UUID id) {
        var model = dbConnection.getDataStore().find(BuyerModel.class).filter(Filters.eq("_id", id)).first();

        if (model == null)
            throw new ItemNotFoundException();

        dbConnection.closeMongoClient();

        return BuyerDbAssembler.toEntity(model);
    }

    @Override
    public Boolean exists(UUID id) {
        var model = dbConnection.getDataStore().find(BuyerModel.class).filter(Filters.eq("_id", id)).first();
        dbConnection.closeMongoClient();

        return model != null;
    }

    @Override
    public void save(Buyer buyer) {
        var dataStore = dbConnection.getDataStore();

        dataStore.save(BuyerDbAssembler.toModel(buyer));
        dbConnection.closeMongoClient();
    }

    @Override
    public List<Buyer> getAll(){
        var buyerList = dbConnection.getDataStore()
                .find(BuyerModel.class).iterator().toList()
                .stream().map(BuyerDbAssembler::toEntity)
                .collect(Collectors.toList());
        dbConnection.closeMongoClient();
        return buyerList;
    }
}
