package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.DetallePedido;
import co.edu.usbcali.market.dto.DetallePedidoDTO;
import co.edu.usbcali.market.request.ActualizarDetallePedidoRequest;
import co.edu.usbcali.market.request.CrearDetallePedidoRequest;
import co.edu.usbcali.market.response.DetallePedidoResponse;

import java.util.List;

public class DetallePedidoMapper {

    public static DetallePedidoDTO domainToDto(DetallePedido detallePedido){
        return DetallePedidoDTO.builder()
                .id(detallePedido.getId())
                .cantidad(detallePedido.getCantidad())
                .valor(detallePedido.getValor())
                .pedidoId(detallePedido.getPedido() == null ?
                        null : detallePedido.getPedido().getId())
                .productoId(detallePedido.getProducto() == null ?
                        null : detallePedido.getProducto().getId())
                .build();
    }

    public static DetallePedido dtoToDomain(DetallePedidoDTO detallePedidoDto){
        return DetallePedido.builder()
                .id(detallePedidoDto.getId())
                .cantidad(detallePedidoDto.getCantidad())
                .valor(detallePedidoDto.getValor())
                .build();
    }

    public static List<DetallePedidoDTO> domainToDtoList(List<DetallePedido> detallePedidos) {
        return detallePedidos.stream().map(DetallePedidoMapper::domainToDto).toList();
    }

    public static List<DetallePedidoResponse> domainToResponseList(List<DetallePedido> detallePedidos) {
        return detallePedidos.stream().map(DetallePedidoMapper::domainToResponse).toList();
    }

    public static List<DetallePedido> dtoToDomainList(List<DetallePedidoDTO> detallePedidosDtos) {
        return detallePedidosDtos.stream().map(DetallePedidoMapper::dtoToDomain).toList();
    }

    public static DetallePedido crearRequestToDomain(CrearDetallePedidoRequest crearDetallePedidoRequest){
        return DetallePedido.builder()
                .cantidad(crearDetallePedidoRequest.getCantidad())
                .valor(crearDetallePedidoRequest.getValor())
                .build();
    }

    public static DetallePedido actualizarRequestToDomain(ActualizarDetallePedidoRequest actualizarDetallePedidoRequest){
        return DetallePedido.builder()
                .id(actualizarDetallePedidoRequest.getId())
                .cantidad(actualizarDetallePedidoRequest.getCantidad())
                .valor(actualizarDetallePedidoRequest.getValor())
                .build();
    }

    public static DetallePedidoResponse domainToResponse(DetallePedido detallePedido){
        return DetallePedidoResponse.builder()
                .id(detallePedido.getId())
                .cantidad(detallePedido.getCantidad())
                .valor(detallePedido.getValor())
                .pedidoId(detallePedido.getPedido().getId())
                .productoId(detallePedido.getProducto().getId())
                .productoNombre(detallePedido.getProducto().getNombre())
                .build();
    }
}
