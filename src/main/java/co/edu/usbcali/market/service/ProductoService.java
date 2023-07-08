package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.Producto;
import co.edu.usbcali.market.dto.ProductoDTO;
import co.edu.usbcali.market.request.ActualizarProductoRequest;
import co.edu.usbcali.market.request.CrearProductoRequest;
import co.edu.usbcali.market.response.PedidoResponse;
import co.edu.usbcali.market.response.ProductoResponse;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> obtenerTodos();
    ProductoResponse buscarPorId(Integer id) throws Exception;
    Producto buscarProductoPorId(Integer id) throws Exception;
    ProductoResponse guardar(CrearProductoRequest crearProductoRequest) throws Exception;
    ProductoResponse actualizar(ActualizarProductoRequest actualizarProductoRequest) throws Exception;
}
