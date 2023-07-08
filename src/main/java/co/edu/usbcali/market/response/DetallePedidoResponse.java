package co.edu.usbcali.market.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoResponse {
    private Integer id;
    private Integer pedidoId;
    private Integer productoId;
    private String productoNombre;
    private BigDecimal cantidad;
    private BigDecimal valor;
}
