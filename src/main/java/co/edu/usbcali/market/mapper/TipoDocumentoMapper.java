package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.domain.EstadoPedido;
import co.edu.usbcali.market.domain.TipoDocumento;
import co.edu.usbcali.market.dto.CategoriaDTO;
import co.edu.usbcali.market.dto.EstadoPedidoDTO;
import co.edu.usbcali.market.dto.TipoDocumentoDTO;

import java.util.List;

public class TipoDocumentoMapper {

    public static TipoDocumentoDTO domainToDto(TipoDocumento tipoDocumento){
        return TipoDocumentoDTO.builder()
                .id(tipoDocumento.getId())
                .descripcion(tipoDocumento.getDescripcion())
                .build();
    }

    public static TipoDocumento dtoToDomain(TipoDocumentoDTO tipoDocumentoDTO){
        return TipoDocumento.builder()
                .id(tipoDocumentoDTO.getId())
                .descripcion(tipoDocumentoDTO.getDescripcion())
                .build();
    }

    public static List<TipoDocumentoDTO> domainToDtoList(List<TipoDocumento> tipoDocumentos) {
        return tipoDocumentos.stream().map(TipoDocumentoMapper::domainToDto).toList();
    }

    public static List<TipoDocumento> dtoToDomainList(List<TipoDocumentoDTO> tipoDocumentosDtos) {
        return tipoDocumentosDtos.stream().map(TipoDocumentoMapper::dtoToDomain).toList();
    }
}
