package ulaval.glo2003.application.assemblers;

import ulaval.glo2003.application.dtos.ProductViewingsDto;
import ulaval.glo2003.application.exceptions.NullReferenceException;
import ulaval.glo2003.entities.product.Product;
import ulaval.glo2003.entities.product.ProductCategory;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ProductViewingsAssembler {
    public static ProductViewingsDto toDto(Product product) {
        if (product == null)
            throw new NullReferenceException();

        var productDto = new ProductViewingsDto();
        productDto.id = product.getId().toString();
        productDto.createdAt = product.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
        productDto.title = product.getTitle();
        productDto.description = product.getDescription();
        productDto.suggestedPrice = product.getSuggestedPrice();
        productDto.viewings = product.getViewings();

        productDto.categories = product.getCategories().stream()
                .map(ProductCategory::enumToString)
                .collect(Collectors.toList());

        return productDto;
    }
}
