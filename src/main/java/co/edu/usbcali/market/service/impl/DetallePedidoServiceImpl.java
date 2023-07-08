package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.DetallePedido;
import co.edu.usbcali.market.exceptions.DetallePedidoException;
import co.edu.usbcali.market.mapper.DetallePedidoMapper;
import co.edu.usbcali.market.mapper.PedidoMapper;
import co.edu.usbcali.market.mapper.ProductoMapper;
import co.edu.usbcali.market.repository.DetallePedidoRepository;
import co.edu.usbcali.market.request.ActualizarDetallePedidoRequest;
import co.edu.usbcali.market.request.CrearDetallePedidoRequest;
import co.edu.usbcali.market.response.DetallePedidoResponse;
import co.edu.usbcali.market.service.DetallePedidoService;
import co.edu.usbcali.market.service.PedidoService;
import co.edu.usbcali.market.service.ProductoService;
import co.edu.usbcali.market.util.Message.DetallePedidoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository, PedidoService pedidoService, ProductoService productoService) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
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
        DetallePedido detallePedido = DetallePedidoMapper.crearRequestToDomain(crearDetallePedidoRequest);

        detallePedido.setPedido(pedidoService.buscarPedidoPorId(crearDetallePedidoRequest.getPedidoId()));
        detallePedido.setProducto(productoService.buscarProductoPorId(crearDetallePedidoRequest.getProductoId()));

        return DetallePedidoMapper.domainToResponse(detallePedidoRepository.save(detallePedido));
    }

    @Override
    public DetallePedidoResponse actualizar(ActualizarDetallePedidoRequest actualizarDetallePedidoRequest) throws Exception {
        DetallePedido detallePedido = buscarDetallePedidoPorId(actualizarDetallePedidoRequest.getId());

        detallePedido.setPedido(pedidoService.buscarPedidoPorId(actualizarDetallePedidoRequest.getPedidoId()));
        detallePedido.setProducto(productoService.buscarProductoPorId(actualizarDetallePedidoRequest.getProductoId()));

        detallePedido.setValor(actualizarDetallePedidoRequest.getValor());
        detallePedido.setCantidad(actualizarDetallePedidoRequest.getCantidad());

        return DetallePedidoMapper.domainToResponse(detallePedidoRepository.save(detallePedido));
    }
}
