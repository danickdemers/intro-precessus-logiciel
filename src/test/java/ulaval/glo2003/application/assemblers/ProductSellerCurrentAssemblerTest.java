package ulaval.glo2003.application.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.application.exceptions.NullReferenceException;
import ulaval.glo2003.entities.product.Product;
import ulaval.glo2003.entities.seller.Seller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.EntityBuilderTestUtils.getDefaultProduct;
import static ulaval.glo2003.EntityBuilderTestUtils.getDefaultSeller;

@ExtendWith(MockitoExtension.class)
public class ProductSellerCurrentAssemblerTest {
    Seller seller;
    Product product;

    @BeforeEach
    void setUp() {
        seller = getDefaultSeller();
        product = getDefaultProduct(seller.getId());
    }

    @Test
    void givenNullProduct_whenToDto_thenResponseThrowsNullReferenceException() {
        assertThrows(NullReferenceException.class,
                () -> ProductSellerCurrentAssembler.toDto(null, new ArrayList<>(), new HashMap<>()));
    }

    @Test
    void givenValidProduct_whenToDto_thenResponseReturnsProductDto() {
        var productDto = ProductSellerCurrentAssembler.toDto(product, new ArrayList<>(), new HashMap<>());

        assertThat(productDto.id).isEqualTo(product.getId().toString());
        assertThat(productDto.createdAt).isEqualTo(product.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        assertThat(productDto.title).isEqualTo(product.getTitle());
        assertThat(productDto.description).isEqualTo(product.getDescription());
        assertThat(productDto.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productDto.categories.size()).isEqualTo(product.getCategories().size());
        assertThat(productDto.offers.count).isEqualTo(0);
    }
}
