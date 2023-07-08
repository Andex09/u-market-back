package co.edu.usbcali.market.request;

import co.edu.usbcali.market.util.Message.PedidoServiceMessages;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearPedidoRequest {

    @NotNull(message = PedidoServiceMessages.CLIENTE_REQUERIDO)
    private Integer clienteId;

    @NotNull(message = PedidoServiceMessages.ESTADO_PEDIDO_REQUERIDO)
    private Integer estadoPedidoId;

    @NotNull(message = PedidoServiceMessages.FECHA_REQUERIDO)
    private LocalDate fecha;

    @NotNull(message = PedidoServiceMessages.TOTAL_REQUERIDO)
    private BigDecimal total;
}
