package co.edu.usbcali.market.dto;

import co.edu.usbcali.market.domain.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String estado;
    private Integer tipoDocumentoId;

}
