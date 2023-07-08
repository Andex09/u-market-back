package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.Cliente;
import co.edu.usbcali.market.request.ActualizarClienteRequest;
import co.edu.usbcali.market.request.CrearClienteRequest;
import co.edu.usbcali.market.response.ClienteResponse;

import java.util.List;

public interface ClienteService {
    List<ClienteResponse> obtenerTodos();
    ClienteResponse buscarPorId(Integer id) throws Exception;
    Cliente buscarClientePorId(Integer id) throws Exception;
    ClienteResponse guardar(CrearClienteRequest crearClienteRequest) throws Exception;
    ClienteResponse actualizar(ActualizarClienteRequest actualizarClienteRequest) throws Exception;

}
