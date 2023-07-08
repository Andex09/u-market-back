package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.dto.CategoriaDTO;
import co.edu.usbcali.market.request.ActualizarCategoriaRequest;
import co.edu.usbcali.market.request.CrearCategoriaRequest;
import co.edu.usbcali.market.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> obtenerTodos();
    CategoriaResponse buscarPorId(Integer id) throws Exception;
    Categoria buscarCategoriaPorId(Integer id) throws Exception;
    CategoriaResponse guardar(CrearCategoriaRequest crearCategoriaRequest) throws Exception;
    CategoriaResponse actualizar(ActualizarCategoriaRequest actualizarCategoriaRequest) throws Exception;
}