package co.edu.usbcali.market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pedidos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clie_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "espe_id", referencedColumnName = "id", nullable = false)
    private EstadoPedido estadoPedido;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 19, precision = 2, nullable = false)
    private BigDecimal total;
}
