package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.TipoDocumento;
import co.edu.usbcali.market.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoDTO> obtenerTodos();
    TipoDocumentoDTO buscarPorId(Integer id) throws Exception;
    TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception;
}
