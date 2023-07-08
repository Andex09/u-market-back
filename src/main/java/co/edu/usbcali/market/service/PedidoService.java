package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.Pedido;
import co.edu.usbcali.market.dto.PedidoDTO;
import co.edu.usbcali.market.request.ActualizarPedidoRequest;
import co.edu.usbcali.market.request.CrearPedidoRequest;
import co.edu.usbcali.market.response.PedidoResponse;

import java.util.List;

public interface PedidoService {
    List<PedidoResponse> obtenerTodos();
    PedidoResponse buscarPorId(Integer id) throws Exception;
    Pedido buscarPedidoPorId(Integer id) throws Exception;
    PedidoResponse guardar(CrearPedidoRequest crearPedidoRequest) throws Exception;
    PedidoResponse actualizar(ActualizarPedidoRequest actualizarPedidoRequest) throws Exception;
}
