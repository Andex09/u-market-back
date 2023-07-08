package co.edu.usbcali.market.response;

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
public class PedidoResponse {
    private Integer id;
    private Integer clienteId;
    private String clienteNombre;
    private Integer estadoPedidoId;
    private String estadoPedidoDescripcion;
    private LocalDate fecha;
    private BigDecimal total;
}
