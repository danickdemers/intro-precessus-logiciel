package ulaval.glo2003.infrastructure.persistence.repository;

import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.application.exceptions.ItemNotFoundException;
import ulaval.glo2003.entities.product.IProductRepository;
import ulaval.glo2003.entities.product.Product;
import ulaval.glo2003.infrastructure.persistence.DbConnection;
import ulaval.glo2003.infrastructure.persistence.assemblers.ProductDbAssembler;
import ulaval.glo2003.infrastructure.persistence.models.ProductModel;
import ulaval.glo2003.infrastructure.persistence.models.SellerModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductRepository implements IProductRepository {
    private final DbConnection dbConnection;

    public ProductRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Product get(UUID id) {
        var model = dbConnection.getDataStore()
                .find(ProductModel.class)
                .filter(Filters.eq("_id", id))
                .first();
        dbConnection.closeMongoClient();

        if (model == null)
            throw new ItemNotFoundException();

        return ProductDbAssembler.toEntity(model);
    }

    @Override
    public Boolean exists(UUID id) {
        var model = dbConnection.getDataStore()
                .find(ProductModel.class)
                .filter(Filters.eq("_id", id))
                .first();
        dbConnection.closeMongoClient();

        return model != null;
    }

    @Override
    public void save(Product product) {
        var dataStore = dbConnection.getDataStore();
        var sellerModel = dataStore.find(SellerModel.class)
                .filter(Filters.eq("_id", product.getSellerId())).first();

        dataStore.save(ProductDbAssembler.toModel(product, sellerModel));
        dbConnection.closeMongoClient();
    }

    @Override
    public List<Product> getAll(){
        var productList =dbConnection.getDataStore()
                .find(ProductModel.class).iterator().toList()
                .stream().map(ProductDbAssembler::toEntity)
                .collect(Collectors.toList());
        dbConnection.closeMongoClient();
        return productList;
    }

}
