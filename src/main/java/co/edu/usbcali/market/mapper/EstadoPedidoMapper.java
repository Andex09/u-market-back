package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.EstadoPedido;
import co.edu.usbcali.market.dto.EstadoPedidoDTO;

import java.util.List;

public class EstadoPedidoMapper {

    public static EstadoPedidoDTO domainToDto(EstadoPedido estadoPedido){
        return EstadoPedidoDTO.builder()
                .id(estadoPedido.getId())
                .descripcion(estadoPedido.getDescripcion())
                .build();
    }

    public static EstadoPedido dtoToDomain(EstadoPedidoDTO estadoPedidoDTO){
        return EstadoPedido.builder()
                .id(estadoPedidoDTO.getId())
                .descripcion(estadoPedidoDTO.getDescripcion())
                .build();
    }

    public static List<EstadoPedidoDTO> domainToDtoList(List<EstadoPedido> estadoPedidos) {
        return estadoPedidos.stream().map(EstadoPedidoMapper::domainToDto).toList();
    }

    public static List<EstadoPedido> dtoToDomainList(List<EstadoPedidoDTO> estadoPedidosDtos) {
        return estadoPedidosDtos.stream().map(EstadoPedidoMapper::dtoToDomain).toList();
    }
}
