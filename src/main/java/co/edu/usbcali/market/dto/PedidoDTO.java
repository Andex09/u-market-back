package co.edu.usbcali.market.dto;

import co.edu.usbcali.market.domain.Cliente;
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
public class PedidoDTO {

    private Integer id;
    private LocalDate fecha;
    private BigDecimal total;
    private Integer estadoPedidoId;
    private Integer clienteId;
}
