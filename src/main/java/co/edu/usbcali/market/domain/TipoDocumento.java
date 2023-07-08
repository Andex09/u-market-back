package co.edu.usbcali.market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tiposdocumento")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descripcion;
}
