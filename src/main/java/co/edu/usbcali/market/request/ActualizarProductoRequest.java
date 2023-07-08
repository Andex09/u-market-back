package co.edu.usbcali.market.request;

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
public class ActualizarProductoRequest {

    @NotNull(message = ProductoServiceMessages.ID_INVALIDO)
    private Integer id;

    @NotBlank(message = ProductoServiceMessages.REFERENCIA_REQUERIDO)
    private String referencia;

    @NotBlank(message = ProductoServiceMessages.NOMBRE_REQUERIDO)
    private String nombre;

    @NotBlank(message = ProductoServiceMessages.DESCRIPCION_REQUERIDO)
    private String descripcion;

    @NotNull(message = ProductoServiceMessages.PRECIO_UNITARIO_REQUERIDO)
    @Min(value = 1, message = ProductoServiceMessages.PRECIO_UNITARIO_MINIMO)
    private BigDecimal precioUnitario;

    @NotNull(message = ProductoServiceMessages.UNIDADES_DISPONIBLES_REQUERIDO)
    @Min(value = 0, message = ProductoServiceMessages.UNIDADES_DISPONIBLES_MINIMO)
    private BigDecimal unidadesDisponibles;

    @NotNull(message = ProductoServiceMessages.CATEGORIA_REQUERIDO)
    private Integer categoriaId;
}
