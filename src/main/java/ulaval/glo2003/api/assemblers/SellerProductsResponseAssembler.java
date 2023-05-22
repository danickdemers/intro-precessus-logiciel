package ulaval.glo2003.api.assemblers;

import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.dtos.ProductViewingsDto;
import ulaval.glo2003.application.exceptions.NullReferenceException;

import java.util.ArrayList;

public class SellerProductsResponseAssembler {
    public static Response toGetCurrentSellerProductsResponse(ArrayList<ProductViewingsDto> productDtos) {
        if (productDtos == null)
            throw new NullReferenceException();

        return Response.status(200).entity(productDtos).build();
    }
}
