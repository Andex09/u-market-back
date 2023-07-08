package co.edu.usbcali.market.service;

import co.edu.usbcali.market.dto.EstadoPedidoDTO;

import java.util.List;

public interface EstadoPedidoService {
    List<EstadoPedidoDTO> obtenerTodos();
    EstadoPedidoDTO buscarPorId(Integer id) throws Exception;
}
