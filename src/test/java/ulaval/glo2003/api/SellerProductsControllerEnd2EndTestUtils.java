package ulaval.glo2003.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SellerProductsControllerEnd2EndTestUtils {
    private static final String HEADER_SELLER_ID = "X-Seller-Id";

    public static Response getCurrentSellerProducts(String sellerId){
        return given().header(HEADER_SELLER_ID, sellerId)
                .urlEncodingEnabled(false)
                .when()
                .get(ControllerPaths.SELLER_PRODUCTS);
    }
}
