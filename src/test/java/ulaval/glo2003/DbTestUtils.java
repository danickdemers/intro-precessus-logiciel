package ulaval.glo2003;

import io.github.cdimascio.dotenv.Dotenv;
import ulaval.glo2003.infrastructure.persistence.DbConnection;

public class DbTestUtils {
    public static DbConnection getTestDbConnexion () {
        var dotenv = Dotenv.load();
        var mongoDbName = dotenv.get("MONGODB");
        var mongoDbUri = dotenv.get("MONGODB_URI");

        return new DbConnection(mongoDbName, mongoDbUri);
    }
}
