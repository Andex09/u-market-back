package co.edu.usbcali.market.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Integer id;
    private Integer tipoDocumentoId;
    private String tipoDocumentoDescripcion;
    private String nombres;
    private String apellidos;
    private String documento;
    private String estado;
}
