package co.edu.usbcali.market.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
}
