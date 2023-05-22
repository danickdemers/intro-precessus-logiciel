package ulaval.glo2003.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.DbTestUtils;
import ulaval.glo2003.Main;
import ulaval.glo2003.infrastructure.persistence.DbConnection;

import java.io.IOException;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static spark.Spark.stop;
import static ulaval.glo2003.EntityBuilderTestUtils.*;
import static ulaval.glo2003.api.End2EndTestUtils.*;
import static ulaval.glo2003.api.SellerProductsControllerEnd2EndTestUtils.getCurrentSellerProducts;

public class SellerProductsControllerEnd2EndTest {
    static DbConnection dbConnection;

    @BeforeAll
    static void startServer() throws IOException {
        Main.inTesting = true;
        Main.main(new String[0]);
        dbConnection = DbTestUtils.getTestDbConnexion();
    }

    @AfterAll
    static void tearDownServer() {
        stop();
        Main.server.shutdown();
    }

    @AfterEach
    void dropDatabase() {
        dbConnection.dropTestDatabase();
    }

    @Test
    void givenValidSellerId_whenGetCurrentSellerProductViewings_thenResponseHasOkResult() {
        var sellerId = createSellerAndGetId(SELLER_NAME, SELLER_BIO, SELLER_BIRTHDATE);
        var request = buildCreateProductRequest(PRODUCT_TITLE, PRODUCT_DESCRIPTION,
                PRODUCT_PRICE, PRODUCT_CATEGORIES);
        createProductForSeller(sellerId, request);

        Response response = getCurrentSellerProducts(sellerId.toString());

        response.then().statusCode(200);
    }

    @Test
    void givenBlankSellerId_whenGetCurrentSellerProductViewings_thenResponseHasItemNotFoundResult() {
        Response response = getCurrentSellerProducts(" ");

        response.then().statusCode(404);
    }

    @Test
    void givenRequestWithMissingHeader_whenGetCurrentSellerProductViewings_thenResponseHasMissingParamResult() {
        Response response = given()
                .urlEncodingEnabled(false)
                .when()
                .get(ControllerPaths.SELLER_PRODUCTS);

        response.then().statusCode(400);
    }

    @Test
    void givenNotFoundSellerId_whenGetCurrentSellerProductViewings_thenResponseHasNotFoundResult() {
        Response response = getCurrentSellerProducts(UUID.randomUUID().toString());

        response.then().statusCode(404);
    }
}
