package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.TipoDocumento;
import co.edu.usbcali.market.dto.TipoDocumentoDTO;
import co.edu.usbcali.market.exceptions.TipoDocumentoException;
import co.edu.usbcali.market.mapper.TipoDocumentoMapper;
import co.edu.usbcali.market.repository.TipoDocumentoRepository;
import co.edu.usbcali.market.service.TipoDocumentoService;
import co.edu.usbcali.market.util.Message.TipoDocumentoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public List<TipoDocumentoDTO> obtenerTodos() {
        return TipoDocumentoMapper.domainToDtoList(tipoDocumentoRepository.findAll());
    }

    @Override
    public TipoDocumentoDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_INVALIDO);
        return tipoDocumentoRepository.findById(id).map(TipoDocumentoMapper::domainToDto)
                .orElseThrow(() -> new TipoDocumentoException(String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public TipoDocumento buscarTipoDocumentoPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, TipoDocumentoServiceMessages.ID_INVALIDO);
        return tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new TipoDocumentoException(String.format(TipoDocumentoServiceMessages.TIPO_DOCUMENTO_NO_ENCONTRADO_POR_ID, id)));
    }
}
