package co.edu.usbcali.market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detallespedido")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedi_id", referencedColumnName = "id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "id", nullable = false)
    private Producto producto;

    @Column(length = 19, precision = 2, nullable = false)
    private BigDecimal cantidad;

    @Column(length = 19, precision = 2, nullable = false)
    private BigDecimal valor;
}
