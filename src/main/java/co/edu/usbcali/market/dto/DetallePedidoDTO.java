package co.edu.usbcali.market.dto;

import co.edu.usbcali.market.domain.Pedido;
import co.edu.usbcali.market.domain.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

    private Integer id;
    private BigDecimal cantidad;
    private BigDecimal valor;
    private Integer pedidoId;
    private Integer productoId;
}
