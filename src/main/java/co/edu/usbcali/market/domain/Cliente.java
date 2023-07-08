package co.edu.usbcali.market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "clientes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tido_id", referencedColumnName = "id", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(length = 50, nullable = false)
    private String nombres;

    @Column(length = 50, nullable = false)
    private String apellidos;

    @Column(length = 50, nullable = false)
    private String documento;

    @Column(length = 1, nullable = false)
    private String estado;
}
