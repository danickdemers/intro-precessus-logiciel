package ulaval.glo2003.api.assemblers;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.application.dtos.HealthDto;
import ulaval.glo2003.application.exceptions.NullReferenceException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HealthResponseAssemblerTest {
    @Test
    void givenNullHealthDto_whenToGetHealthResponse_thenThrowsNullReferenceException() {
        assertThrows(NullReferenceException.class,
                () -> HealthResponseAssembler.toGetHealthResponse(null));
    }

    @Test
    void givenDtoWithApiPropertyFalse_whenToGetHealthResponse_thenResponseHasInternalServerErrorStatus() {
        var healthDto = new HealthDto();
        healthDto.api = false;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        assertThat(getHealthResponse.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    @Test
    void givenDtoWithApiPropertyFalse_whenToGetHealthResponse_thenResponseContainsHealthDto() {
        var healthDto = new HealthDto();
        healthDto.api = false;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        var entity = ((HealthDto) getHealthResponse.getEntity());
        assertThat(entity.api).isEqualTo(healthDto.api);
        assertThat(entity.db).isEqualTo(healthDto.db);
    }

    @Test
    void givenDtoWithDbPropertyFalse_whenToGetHealthResponse_thenResponseHasInternalServerErrorStatus() {
        var healthDto = new HealthDto();
        healthDto.db = false;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        assertThat(getHealthResponse.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    @Test
    void givenDtoWithDbPropertyFalse_whenToGetHealthResponse_thenResponseContainsHealthDto() {
        var healthDto = new HealthDto();
        healthDto.db = false;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        var entity = ((HealthDto) getHealthResponse.getEntity());
        assertThat(entity.api).isEqualTo(healthDto.api);
        assertThat(entity.db).isEqualTo(healthDto.db);
    }

    @Test
    void givenDtoWithApiAndDbPropertiesTrue_whenToGetHealthResponse_thenResponseHasOkStatus() {
        var healthDto = new HealthDto();
        healthDto.api = true;
        healthDto.db = true;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        assertThat(getHealthResponse.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    void givenDtoWithApiAndDbPropertiesTrue_whenToGetHealthResponse_thenResponseContainsHealthDto() {
        var healthDto = new HealthDto();
        healthDto.api = true;
        healthDto.db = true;

        var getHealthResponse = HealthResponseAssembler.toGetHealthResponse(healthDto);

        var entity = ((HealthDto) getHealthResponse.getEntity());
        assertThat(entity.api).isEqualTo(healthDto.api);
        assertThat(entity.db).isEqualTo(healthDto.db);
    }
}
