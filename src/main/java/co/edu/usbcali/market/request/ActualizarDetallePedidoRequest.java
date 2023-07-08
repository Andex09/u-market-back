package co.edu.usbcali.market.request;

import co.edu.usbcali.market.util.Message.CategoriaServiceMessages;
import co.edu.usbcali.market.util.Message.DetallePedidoServiceMessages;
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
public class ActualizarDetallePedidoRequest {

    @NotNull(message = CategoriaServiceMessages.ID_INVALIDO)
    private Integer id;

    @NotNull(message = DetallePedidoServiceMessages.PEDIDO_REQUERIDO)
    private Integer pedidoId;

    @NotNull(message = DetallePedidoServiceMessages.PRODUCTO_REQUERIDO)
    private Integer productoId;

    @NotNull(message = DetallePedidoServiceMessages.CANTIDAD_REQUERIDO)
    private BigDecimal cantidad;

    @NotNull(message = DetallePedidoServiceMessages.VALOR_REQUERIDO)
    private BigDecimal valor;
}
