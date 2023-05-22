package ulaval.glo2003;

import io.github.cdimascio.dotenv.Dotenv;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.SellersController;
import ulaval.glo2003.api.HealthController;
import ulaval.glo2003.api.ProductsController;
import ulaval.glo2003.api.SellersProductsController;
import ulaval.glo2003.api.ProductOffersController;
import ulaval.glo2003.api.exceptions.mappers.InvalidParamExceptionMapper;
import ulaval.glo2003.api.exceptions.mappers.ItemNotFoundExceptionMapper;
import ulaval.glo2003.api.exceptions.mappers.MissingParamExceptionMapper;
import ulaval.glo2003.entities.buyer.Buyer;
import ulaval.glo2003.entities.buyer.BuyerFactory;
import ulaval.glo2003.entities.offer.OfferFactory;
import ulaval.glo2003.entities.product.Product;
import ulaval.glo2003.entities.product.ProductFactory;
import ulaval.glo2003.entities.seller.Seller;
import ulaval.glo2003.entities.seller.SellerFactory;
import ulaval.glo2003.infrastructure.persistence.DbConnection;
import ulaval.glo2003.infrastructure.persistence.repository.BuyerRepository;
import ulaval.glo2003.infrastructure.persistence.repository.InMemoryRepository;
import ulaval.glo2003.infrastructure.persistence.repository.OfferRepository;
import ulaval.glo2003.infrastructure.persistence.repository.ProductRepository;
import ulaval.glo2003.infrastructure.persistence.repository.SellerRepository;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static HttpServer server;
    public static String url;
    public static boolean inTesting = false;

    public static void main(String[] args) throws IOException {
        var sellerInMemoryRepository = new InMemoryRepository<Seller>();
        var productInMemoryRepository = new InMemoryRepository<Product>();
        var buyerInMemoryRepository = new InMemoryRepository<Buyer>();

        var sellerFactory = new SellerFactory();
        var productFactory = new ProductFactory();
        var offerFactory = new OfferFactory();
        var buyerFactory = new BuyerFactory();

        var dotenv = Dotenv.load();
        var port = dotenv.get("PORT");
        var mongoDbName = dotenv.get("MONGODB");
        var mongoDbUri = dotenv.get("MONGODB_URI");
        var environmentString = dotenv.get("ENVIRONMENT");

        if (!inTesting) {
            if (environmentString.equals("test")) {
                url = "http://localhost:" + port + "/";
            } else  {
                url = "http://0.0.0.0:" + port + "/";
            }
        } else
            url = "http://localhost:" + port + "/";

        var dbConnection = new DbConnection(mongoDbName, mongoDbUri);
        var sellerRepository = new SellerRepository(dbConnection);
        var productRepository = new ProductRepository(dbConnection);
        var offerRepository = new OfferRepository(dbConnection);
        var buyerRepository = new BuyerRepository(dbConnection);

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(sellerInMemoryRepository)
                .register(productInMemoryRepository)
                .register(buyerInMemoryRepository)
                .register(productFactory)
                .register(offerFactory)
                .register(buyerFactory)
                .register(new HealthController(
                        dbConnection))
                .register(new SellersController(
                        sellerFactory,
                        sellerRepository,
                        productRepository,
                        offerRepository,
                        buyerRepository))
                .register(new ProductsController(
                        productFactory,
                        productRepository,
                        sellerRepository,
                        offerRepository))
                .register(new ProductOffersController(
                        offerFactory,
                        buyerFactory,
                        offerRepository,
                        buyerRepository,
                        productRepository))
                .register(new SellersProductsController(productRepository, sellerRepository))
                .register(InvalidParamExceptionMapper.class)
                .register(ItemNotFoundExceptionMapper.class)
                .register(MissingParamExceptionMapper.class)
                .register(dbConnection)
                .register(sellerRepository)
                .register(productRepository)
                .register(offerRepository);

        URI uri = URI.create(url);

        server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
