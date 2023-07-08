package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.exceptions.CategoriaException;
import co.edu.usbcali.market.mapper.CategoriaMapper;
import co.edu.usbcali.market.repository.CategoriaRepository;
import co.edu.usbcali.market.request.ActualizarCategoriaRequest;
import co.edu.usbcali.market.request.CrearCategoriaRequest;
import co.edu.usbcali.market.response.CategoriaResponse;
import co.edu.usbcali.market.service.CategoriaService;
import co.edu.usbcali.market.util.Message.CategoriaServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaResponse> obtenerTodos() {
        return CategoriaMapper.domainToResponseList(categoriaRepository.findAll());
    }

    @Override
    public CategoriaResponse buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_INVALIDO);
        return categoriaRepository.findById(id).map(CategoriaMapper::domainToResponse)
                .orElseThrow(() -> new CategoriaException(String.format(CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public Categoria buscarCategoriaPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_INVALIDO);
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaException(String.format(CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public CategoriaResponse guardar(CrearCategoriaRequest crearCategoriaRequest) throws Exception {
        validarSiExistePorNombre(crearCategoriaRequest.getNombre());

        Categoria categoria = CategoriaMapper.crearRequestToDomain(crearCategoriaRequest);
        return CategoriaMapper.domainToResponse(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponse actualizar(ActualizarCategoriaRequest actualizarCategoriaRequest) throws Exception {
        validarSiExistePorNombreYOtroId(actualizarCategoriaRequest.getNombre(), actualizarCategoriaRequest.getId());

        Categoria categoria = buscarCategoriaPorId(actualizarCategoriaRequest.getId());
        categoria.setNombre(actualizarCategoriaRequest.getNombre());
        categoria.setDescripcion(actualizarCategoriaRequest.getDescripcion());

        return CategoriaMapper.domainToResponse(categoriaRepository.save(categoria));
    }

    public void validarSiExistePorNombre(String nombre) throws Exception {
        boolean existePorNombre = categoriaRepository.existsByNombreIgnoreCase(nombre);
        if(existePorNombre) throw new CategoriaException(String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, nombre));
    }

    public void validarSiExistePorNombreYOtroId(String nombre, Integer id) throws Exception {
        boolean existePorNombreYOtroId = categoriaRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);
        if(existePorNombreYOtroId) throw new CategoriaException(String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, nombre));
    }
}
