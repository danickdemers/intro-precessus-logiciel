package ulaval.glo2003.api.assemblers;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.application.assemblers.ProductViewingsAssembler;
import ulaval.glo2003.application.dtos.ProductViewingsDto;
import ulaval.glo2003.application.exceptions.NullReferenceException;
import ulaval.glo2003.entities.product.Product;
import ulaval.glo2003.entities.seller.Seller;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.EntityBuilderTestUtils.getDefaultProduct;
import static ulaval.glo2003.EntityBuilderTestUtils.getDefaultSeller;

@ExtendWith(MockitoExtension.class)
public class SellerProductsResponseAssemblerTest {
    Seller seller;
    Product product;

    @BeforeEach
    void setUp() {
        seller = getDefaultSeller();
        product = getDefaultProduct(seller.getId());
    }

    @Test
    void givenNullProductList_whenToGetCurrentSellerProductsResponse_thenThrowsNullReferenceException() {
        assertThrows(NullReferenceException.class,
                () -> SellerProductsResponseAssembler.toGetCurrentSellerProductsResponse(null));
    }

    @Test
    void givenValidProductList_whenToGetCurrentSellerProductsResponse_thenResponseHasOkStatus() {
        var productDtos = new ArrayList<ProductViewingsDto>();
        productDtos.add(ProductViewingsAssembler.toDto(product));

        Response getCurrentSellerProductsResponse = SellerProductsResponseAssembler
                .toGetCurrentSellerProductsResponse(productDtos);

        assertThat(getCurrentSellerProductsResponse.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    void givenValidProductList_whenToGetCurrentSellerProductsResponse_thenResponseContainsProductList() {
        var productDtos = new ArrayList<ProductViewingsDto>();
        productDtos.add(ProductViewingsAssembler.toDto(product));

        Response getCurrentSellerProductsResponse = SellerProductsResponseAssembler
                .toGetCurrentSellerProductsResponse(productDtos);

        var responseProductDtos = (ArrayList<ProductViewingsDto>) getCurrentSellerProductsResponse.getEntity();
        assertThat(responseProductDtos).contains(productDtos.get(0));
    }
}
