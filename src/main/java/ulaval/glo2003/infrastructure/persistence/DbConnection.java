package ulaval.glo2003.infrastructure.persistence;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.MapperOptions;
import org.bson.UuidRepresentation;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DbConnection {
    private final String mongoUrl;
    private final String db;

    private MongoClient mongoClient;


    public DbConnection(String db, String mongoUrl) {
        this.db = db;
        this.mongoUrl = mongoUrl;
    }

    public boolean canConnectToProduction() {
        try (MongoClient mongoClient = MongoClients.create(getMongoSettings(mongoUrl))) {
            try {
                var database = mongoClient.getDatabase(db);
                var collection = database.listCollectionNames().first();
                mongoClient.close();

                return collection != null;

            } catch (MongoException me) {
                return false;
            }
        }
    }

    public Datastore getDataStore() {
        var morphiaSettings = MapperOptions.builder().uuidRepresentation(UuidRepresentation.JAVA_LEGACY).build();
        mongoClient = MongoClients.create(getMongoSettings(mongoUrl));
        var datastore = Morphia.createDatastore(mongoClient, db, morphiaSettings);
        datastore.getMapper().mapPackage("ulaval.glo2003.infrastructure.persistence.model");

        return datastore;
    }

    public void dropTestDatabase(){
        var mongoClient = MongoClients.create(getMongoSettings(mongoUrl));
        var database = mongoClient.getDatabase("floppa-dev");
        database.drop();
        database.createCollection("bidon");
        mongoClient.close();
    }

    private MongoClientSettings getMongoSettings(String url) {
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(url))
                .applyToConnectionPoolSettings(builder ->
                        builder.maxConnectionIdleTime(15, SECONDS))
                .applyToClusterSettings(builder ->
                        builder.serverSelectionTimeout(15, SECONDS))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
    }

    public void closeMongoClient() {
        mongoClient.close();
    }
}
