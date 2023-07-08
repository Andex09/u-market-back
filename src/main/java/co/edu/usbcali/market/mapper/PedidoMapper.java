package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.Pedido;
import co.edu.usbcali.market.dto.PedidoDTO;
import co.edu.usbcali.market.request.ActualizarPedidoRequest;
import co.edu.usbcali.market.request.CrearPedidoRequest;
import co.edu.usbcali.market.response.PedidoResponse;

import java.util.List;

public class PedidoMapper {

    public static PedidoDTO domainToDto(Pedido pedido){
        return PedidoDTO.builder()
                .id(pedido.getId())
                .fecha(pedido.getFecha())
                .total(pedido.getTotal())
                .estadoPedidoId(pedido.getEstadoPedido() == null ?
                            null : pedido.getEstadoPedido().getId())
                .clienteId(pedido.getCliente() == null ?
                            null : pedido.getCliente().getId())
                .build();
    }

    public static Pedido dtoToDomain(PedidoDTO pedidoDto){
        return Pedido.builder()
                .id(pedidoDto.getId())
                .fecha(pedidoDto.getFecha())
                .total(pedidoDto.getTotal())
                .build();
    }

    public static List<PedidoDTO> domainToDtoList(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoMapper::domainToDto).toList();
    }

    public static List<PedidoResponse> domainToResponseList(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoMapper::domainToResponse).toList();
    }

    public static List<Pedido> dtoToDomainList(List<PedidoDTO> pedidosDtos) {
        return pedidosDtos.stream().map(PedidoMapper::dtoToDomain).toList();
    }

    public static Pedido crearRequestToDomain(CrearPedidoRequest crearPedidoRequest){
        return Pedido.builder()
                .total(crearPedidoRequest.getTotal())
                .fecha(crearPedidoRequest.getFecha())
                .build();
    }

    public static Pedido actualizarRequestToDomain(ActualizarPedidoRequest actualizarPedidoRequest){
        return Pedido.builder()
                .id(actualizarPedidoRequest.getId())
                .total(actualizarPedidoRequest.getTotal())
                .fecha(actualizarPedidoRequest.getFecha())
                .build();
    }

    public static PedidoResponse domainToResponse(Pedido pedido){
        return PedidoResponse.builder()
                .id(pedido.getId())
                .total(pedido.getTotal())
                .fecha(pedido.getFecha())
                .clienteId(pedido.getCliente().getId())
                .clienteNombre(pedido.getCliente().getNombres() + " " + pedido.getCliente().getApellidos())
                .estadoPedidoId(pedido.getEstadoPedido().getId())
                .estadoPedidoDescripcion(pedido.getEstadoPedido().getDescripcion())
                .build();
    }
}
