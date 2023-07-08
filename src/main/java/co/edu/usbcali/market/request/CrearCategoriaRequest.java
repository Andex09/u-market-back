package co.edu.usbcali.market.request;

import co.edu.usbcali.market.util.Message.CategoriaServiceMessages;
import co.edu.usbcali.market.util.Message.ProductoServiceMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearCategoriaRequest {

    @NotBlank(message = CategoriaServiceMessages.NOMBRE_REQUERIDO)
    private String nombre;

    @NotBlank(message = CategoriaServiceMessages.DESCRIPCION_REQUERIDO)
    private String descripcion;
}
