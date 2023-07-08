package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.DetallePedido;
import co.edu.usbcali.market.domain.Producto;
import co.edu.usbcali.market.exceptions.DetallePedidoException;
import co.edu.usbcali.market.mapper.DetallePedidoMapper;
import co.edu.usbcali.market.repository.DetallePedidoRepository;
import co.edu.usbcali.market.repository.ProductoRepository;
import co.edu.usbcali.market.request.ActualizarDetallePedidoRequest;
import co.edu.usbcali.market.request.CrearDetallePedidoRequest;
import co.edu.usbcali.market.response.DetallePedidoResponse;
import co.edu.usbcali.market.service.DetallePedidoService;
import co.edu.usbcali.market.service.PedidoService;
import co.edu.usbcali.market.service.ProductoService;
import co.edu.usbcali.market.util.Message.DetallePedidoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoService pedidoService;
    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, PedidoService pedidoService, ProductoService productoService, ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<DetallePedidoResponse> obtenerTodos() {
        return DetallePedidoMapper.domainToResponseList(detallePedidoRepository.findAll());
    }

    @Override
    public DetallePedidoResponse buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, DetallePedidoServiceMessages.ID_INVALIDO);

        return detallePedidoRepository.findById(id).map(DetallePedidoMapper::domainToResponse)
                .orElseThrow(() -> new DetallePedidoException(String.format(DetallePedidoServiceMessages.DETALLE_PEDIDO_NO_ENCONTRADO_POR_ID, id)));

    }

    @Override
    public DetallePedido buscarDetallePedidoPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, DetallePedidoServiceMessages.ID_INVALIDO);

        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new DetallePedidoException(String.format(DetallePedidoServiceMessages.DETALLE_PEDIDO_NO_ENCONTRADO_POR_ID, id)));

    }

    @Override
    public DetallePedidoResponse guardar(CrearDetallePedidoRequest crearDetallePedidoRequest) throws Exception {
        ValidarSiHaySuficientesProductos(crearDetallePedidoRequest.getProductoId(), crearDetallePedidoRequest.getCantidad());

        DetallePedido detallePedido = DetallePedidoMapper.crearRequestToDomain(crearDetallePedidoRequest);

        detallePedido.setPedido(pedidoService.buscarPedidoPorId(crearDetallePedidoRequest.getPedidoId()));
        detallePedido.setProducto(productoService.buscarProductoPorId(crearDetallePedidoRequest.getProductoId()));

        ActualizarCantidadProductos(crearDetallePedidoRequest.getProductoId(), crearDetallePedidoRequest.getCantidad(), false);

        return DetallePedidoMapper.domainToResponse(detallePedidoRepository.save(detallePedido));
    }

    private void ActualizarCantidadProductos(Integer productoId, BigDecimal cantidad, Boolean esAgregar) throws Exception {
        Producto producto = productoService.buscarProductoPorId(productoId);
        if(esAgregar){
            producto.setUnidadesDisponibles(producto.getUnidadesDisponibles().add(cantidad));
        }
        else{
            producto.setUnidadesDisponibles(producto.getUnidadesDisponibles().subtract(cantidad));
        }
        productoRepository.save(producto);
    }

    @Override
    public DetallePedidoResponse actualizar(ActualizarDetallePedidoRequest actualizarDetallePedidoRequest) throws Exception {
        DetallePedido detallePedido = buscarDetallePedidoPorId(actualizarDetallePedidoRequest.getId());

        validarSiHaySuficientesProductosActualizacion(actualizarDetallePedidoRequest, detallePedido);

        if(detallePedido.getProducto().getId() != actualizarDetallePedidoRequest.getProductoId()){
            ActualizarCantidadProductos(
                    detallePedido.getProducto().getId(),
                    detallePedido.getCantidad(),
                    true);
            ActualizarCantidadProductos(
                    actualizarDetallePedidoRequest.getProductoId(),
                    actualizarDetallePedidoRequest.getCantidad(),
                    false);
        }
        else {
            ActualizarCantidadProductos(
                    actualizarDetallePedidoRequest.getPedidoId(),
                    actualizarDetallePedidoRequest.getCantidad().subtract(detallePedido.getCantidad()),
                    false);
        }

        detallePedido.setPedido(pedidoService.buscarPedidoPorId(actualizarDetallePedidoRequest.getPedidoId()));
        detallePedido.setProducto(productoService.buscarProductoPorId(actualizarDetallePedidoRequest.getProductoId()));

        detallePedido.setValor(actualizarDetallePedidoRequest.getValor());
        detallePedido.setCantidad(actualizarDetallePedidoRequest.getCantidad());

        return DetallePedidoMapper.domainToResponse(detallePedidoRepository.save(detallePedido));
    }

    private void ValidarSiHaySuficientesProductos(Integer productoId, BigDecimal cantidad) throws Exception {
        Producto producto = productoService.buscarProductoPorId(productoId);
        if( producto.getUnidadesDisponibles().compareTo(cantidad) == -1) throw new DetallePedidoException(DetallePedidoServiceMessages.NO_HAY_UNIDADES_SUFICIENTES);
    }

    private void validarSiHaySuficientesProductosActualizacion(ActualizarDetallePedidoRequest actualizarDetallePedidoRequest, DetallePedido detallePedidoOriginal) throws Exception {
        Producto producto = productoService.buscarProductoPorId(actualizarDetallePedidoRequest.getProductoId());
        BigDecimal unidadesAdicionales;
        if(actualizarDetallePedidoRequest.getProductoId() == detallePedidoOriginal.getProducto().getId()){
            unidadesAdicionales = actualizarDetallePedidoRequest.getCantidad().subtract(detallePedidoOriginal.getCantidad());
        }
        else{
            unidadesAdicionales = actualizarDetallePedidoRequest.getCantidad();
        }

        if( unidadesAdicionales.compareTo(producto.getUnidadesDisponibles()) == 1) throw new DetallePedidoException(DetallePedidoServiceMessages.NO_HAY_UNIDADES_SUFICIENTES);
    }
}
