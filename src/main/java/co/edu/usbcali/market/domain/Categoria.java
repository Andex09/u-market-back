package co.edu.usbcali.market.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "categorias")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    //@OneToMany(mappedBy = "categoria")
    //List<Producto> productos;
}
