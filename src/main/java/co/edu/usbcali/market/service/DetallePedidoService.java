package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.DetallePedido;
import co.edu.usbcali.market.dto.DetallePedidoDTO;
import co.edu.usbcali.market.request.ActualizarDetallePedidoRequest;
import co.edu.usbcali.market.request.CrearDetallePedidoRequest;
import co.edu.usbcali.market.response.DetallePedidoResponse;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoResponse> obtenerTodos();
    DetallePedidoResponse buscarPorId(Integer id) throws Exception;
    DetallePedido buscarDetallePedidoPorId(Integer id) throws Exception;
    DetallePedidoResponse guardar(CrearDetallePedidoRequest crearDetallePedidoRequest) throws Exception;
    DetallePedidoResponse actualizar(ActualizarDetallePedidoRequest actualizarDetallePedidoRequest) throws Exception;
}
