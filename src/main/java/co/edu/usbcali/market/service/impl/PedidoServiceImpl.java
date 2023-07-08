package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.Pedido;
import co.edu.usbcali.market.exceptions.ClienteException;
import co.edu.usbcali.market.exceptions.PedidoException;
import co.edu.usbcali.market.mapper.EstadoPedidoMapper;
import co.edu.usbcali.market.mapper.PedidoMapper;
import co.edu.usbcali.market.repository.PedidoRepository;
import co.edu.usbcali.market.request.ActualizarPedidoRequest;
import co.edu.usbcali.market.request.CrearPedidoRequest;
import co.edu.usbcali.market.response.PedidoResponse;
import co.edu.usbcali.market.service.ClienteService;
import co.edu.usbcali.market.service.EstadoPedidoService;
import co.edu.usbcali.market.service.PedidoService;
import co.edu.usbcali.market.util.Message.PedidoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    private final ClienteService clienteService;
    private final EstadoPedidoService estadoPedidoService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteService clienteService, EstadoPedidoService estadoPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.estadoPedidoService = estadoPedidoService;
    }

    @Override
    public List<PedidoResponse> obtenerTodos() {
        return PedidoMapper.domainToResponseList(pedidoRepository.findAll());
    }

    @Override
    public PedidoResponse buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, PedidoServiceMessages.ID_INVALIDO);
        return pedidoRepository.findById(id).map(PedidoMapper::domainToResponse)
                .orElseThrow(() -> new PedidoException(String.format(PedidoServiceMessages.PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public Pedido buscarPedidoPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, PedidoServiceMessages.ID_INVALIDO);
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ClienteException(String.format(PedidoServiceMessages.PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public PedidoResponse guardar(CrearPedidoRequest crearPedidoRequest) throws Exception {
        Pedido pedido = PedidoMapper.crearRequestToDomain(crearPedidoRequest);

        pedido.setCliente(clienteService.buscarClientePorId(crearPedidoRequest.getClienteId()));
        pedido.setEstadoPedido(EstadoPedidoMapper.dtoToDomain(estadoPedidoService.buscarPorId(crearPedidoRequest.getEstadoPedidoId())));

        return PedidoMapper.domainToResponse(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoResponse actualizar(ActualizarPedidoRequest actualizarPedidoRequest) throws Exception {
        Pedido pedido = buscarPedidoPorId(actualizarPedidoRequest.getId());

        pedido.setCliente(clienteService.buscarClientePorId(actualizarPedidoRequest.getClienteId()));
        pedido.setEstadoPedido(EstadoPedidoMapper.dtoToDomain(estadoPedidoService.buscarPorId(actualizarPedidoRequest.getEstadoPedidoId())));

        pedido.setFecha(actualizarPedidoRequest.getFecha());
        pedido.setTotal(actualizarPedidoRequest.getTotal());

        return PedidoMapper.domainToResponse(pedidoRepository.save(pedido));
    }
}
