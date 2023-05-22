package ulaval.glo2003.application.assemblers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.application.exceptions.NullReferenceException;
import ulaval.glo2003.entities.product.Product;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ulaval.glo2003.EntityBuilderTestUtils.*;

@ExtendWith(MockitoExtension.class)
public class ProductViewingsAssemblerTest {
    Product product;

    @BeforeEach
    void setUp() {
        product = getDefaultProduct(getDefaultSeller().getId());
    }

    @Test
    void givenNullProduct_whenToDto_thenThrowsNullReferenceException() {
        assertThrows(NullReferenceException.class, () -> ProductViewingsAssembler.toDto(null));
    }

    @Test
    void givenValidProduct_whenToDto_thenResponseReturnsProductDto() {
        var productDto = ProductViewingsAssembler.toDto(product);

        assertThat(productDto.id).isEqualTo(product.getId().toString());
        assertThat(productDto.title).isEqualTo(product.getTitle());
        assertThat(productDto.description).isEqualTo(product.getDescription());
        assertThat(productDto.suggestedPrice).isEqualTo(product.getSuggestedPrice());
        assertThat(productDto.viewings).isEqualTo(product.getViewings());
        assertThat(productDto.categories).isEqualTo(product.getCategoriesAsString());
    }
}
