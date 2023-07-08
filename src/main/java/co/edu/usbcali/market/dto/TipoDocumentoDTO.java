package co.edu.usbcali.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDTO {

    private Integer id;
    private String descripcion;
}
