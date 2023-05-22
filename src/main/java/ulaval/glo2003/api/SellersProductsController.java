package ulaval.glo2003.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.api.assemblers.SellerProductsResponseAssembler;
import ulaval.glo2003.api.utils.StringUtils;
import ulaval.glo2003.application.assemblers.ProductViewingsAssembler;
import ulaval.glo2003.application.dtos.ProductViewingsDto;
import ulaval.glo2003.application.exceptions.ItemNotFoundException;
import ulaval.glo2003.application.exceptions.MissingParamException;
import ulaval.glo2003.infrastructure.persistence.repository.ProductRepository;
import ulaval.glo2003.infrastructure.persistence.repository.SellerRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Path(ControllerPaths.SELLER_PRODUCTS)
public class SellersProductsController {
    private final String HEADER_SELLER_ID = "X-Seller-Id";

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public SellersProductsController(ProductRepository productRepository,
                                     SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @GET
    public Response getCurrentSellerProductViewings(@HeaderParam(HEADER_SELLER_ID) String sellerId) {

        if (sellerId == null)
            throw new MissingParamException();

        var sellerUUID = StringUtils.parseStringToUUID(sellerId);
        if (!sellerRepository.exists(sellerUUID))
            throw new ItemNotFoundException();

        var sellerProducts = productRepository.getAll().stream()
                .filter(product -> product.getSellerId().equals(sellerUUID))
                .collect(Collectors.toList());

        var productDtos = new ArrayList<ProductViewingsDto>();
        for (var product : sellerProducts) {
            productDtos.add(ProductViewingsAssembler.toDto(product));
        }

        return SellerProductsResponseAssembler.toGetCurrentSellerProductsResponse(productDtos);
    }
}
