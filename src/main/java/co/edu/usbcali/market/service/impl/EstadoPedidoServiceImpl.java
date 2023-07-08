package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.dto.EstadoPedidoDTO;
import co.edu.usbcali.market.exceptions.EstadoPedidoException;
import co.edu.usbcali.market.mapper.EstadoPedidoMapper;
import co.edu.usbcali.market.repository.EstadoPedidoRepository;
import co.edu.usbcali.market.service.EstadoPedidoService;
import co.edu.usbcali.market.util.Message.EstadoPedidoServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {
    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoServiceImpl(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    @Override
    public List<EstadoPedidoDTO> obtenerTodos() {
        return EstadoPedidoMapper.domainToDtoList(estadoPedidoRepository.findAll());
    }

    @Override
    public EstadoPedidoDTO buscarPorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, EstadoPedidoServiceMessages.ID_INVALIDO);
        return estadoPedidoRepository.findById(id).map(EstadoPedidoMapper::domainToDto)
                .orElseThrow(() -> new EstadoPedidoException(String.format(EstadoPedidoServiceMessages.ESTADO_PEDIDO_NO_ENCONTRADO_POR_ID, id)));
    }
}
