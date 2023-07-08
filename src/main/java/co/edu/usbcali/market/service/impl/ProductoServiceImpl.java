package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.Producto;
import co.edu.usbcali.market.exceptions.ProductoException;
import co.edu.usbcali.market.mapper.ProductoMapper;
import co.edu.usbcali.market.repository.ProductoRepository;
import co.edu.usbcali.market.request.ActualizarProductoRequest;
import co.edu.usbcali.market.request.CrearProductoRequest;
import co.edu.usbcali.market.response.ProductoResponse;
import co.edu.usbcali.market.service.ProductoService;
import co.edu.usbcali.market.util.Message.CategoriaServiceMessages;
import co.edu.usbcali.market.util.Message.ProductoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaServiceImpl categoriaService;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaServiceImpl categoriaService) {
        this.productoRepository = productoRepository;
        this.categoriaService = categoriaService;
    }

    @Override
    public List<ProductoResponse> obtenerTodos() {
        return ProductoMapper.domainToResponseList(productoRepository.findAll());
    }

    @Override
    public ProductoResponse buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, ProductoServiceMessages.ID_INVALIDO);
        return productoRepository.findById(id).map(ProductoMapper::domainToResponse)
                .orElseThrow(() -> new ProductoException(String.format(ProductoServiceMessages.PRODUCTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public Producto buscarProductoPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, CategoriaServiceMessages.ID_INVALIDO);
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException(String.format(ProductoServiceMessages.PRODUCTO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public ProductoResponse guardar(CrearProductoRequest crearProductoRequest) throws Exception {
        validarSiExistePorReferencia(crearProductoRequest.getReferencia());

        Producto producto = ProductoMapper.crearRequestToDomain(crearProductoRequest);
        producto.setCategoria(categoriaService.buscarCategoriaPorId(crearProductoRequest.getCategoriaId()));

        return ProductoMapper.domainToResponse(productoRepository.save(producto));
    }

    @Override
    public ProductoResponse actualizar(ActualizarProductoRequest actualizarProductoRequest) throws Exception {
        validarSiExistePorReferenciaYOtroId(actualizarProductoRequest.getReferencia(), actualizarProductoRequest.getId());

        Producto producto = buscarProductoPorId(actualizarProductoRequest.getId());
        producto.setCategoria(categoriaService.buscarCategoriaPorId(actualizarProductoRequest.getCategoriaId()));

        producto.setNombre(actualizarProductoRequest.getNombre());
        producto.setDescripcion(actualizarProductoRequest.getDescripcion());
        producto.setReferencia(actualizarProductoRequest.getReferencia());
        producto.setPrecioUnitario(actualizarProductoRequest.getPrecioUnitario());
        producto.setUnidadesDisponibles(actualizarProductoRequest.getUnidadesDisponibles());

        return ProductoMapper.domainToResponse(productoRepository.save(producto));
    }

    public void validarSiExistePorReferencia(String referencia) throws Exception {
        boolean existe = productoRepository.existsByReferenciaIgnoreCase(referencia);
        if(existe) throw new Exception(String.format(ProductoServiceMessages.EXISTE_POR_REFERENCIA, referencia));
    }

    public void validarSiExistePorReferenciaYOtroId(String referencia, Integer id) throws Exception {
        boolean existe = productoRepository.existsByReferenciaIgnoreCaseAndIdNot(referencia, id);
        if(existe) throw new Exception(String.format(ProductoServiceMessages.EXISTE_POR_REFERENCIA, referencia));
    }
}
