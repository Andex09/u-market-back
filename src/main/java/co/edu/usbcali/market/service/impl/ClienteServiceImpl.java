package co.edu.usbcali.market.service.impl;

import co.edu.usbcali.market.domain.Cliente;
import co.edu.usbcali.market.exceptions.ClienteException;
import co.edu.usbcali.market.mapper.ClienteMapper;
import co.edu.usbcali.market.mapper.TipoDocumentoMapper;
import co.edu.usbcali.market.repository.ClienteRepository;
import co.edu.usbcali.market.request.ActualizarClienteRequest;
import co.edu.usbcali.market.request.CrearClienteRequest;
import co.edu.usbcali.market.response.ClienteResponse;
import co.edu.usbcali.market.service.ClienteService;
import co.edu.usbcali.market.service.TipoDocumentoService;
import co.edu.usbcali.market.util.Message.ClienteServiceMessages;
import co.edu.usbcali.market.util.ValidationsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoDocumentoService tipoDocumentoService;
    public ClienteServiceImpl(ClienteRepository clienteRepository, TipoDocumentoService tipoDocumentoService) {
        this.clienteRepository = clienteRepository;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @Override
    public List<ClienteResponse> obtenerTodos() {
        return ClienteMapper.domainToResponseList(clienteRepository.findAll());
    }

    @Override
    public ClienteResponse buscarPorId(Integer id) throws Exception{
        ValidationsUtil.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_INVALIDO);

        return clienteRepository.findById(id).map(ClienteMapper::domainToResponse)
                                .orElseThrow(() -> new ClienteException(String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));

    }

    @Override
    public Cliente buscarClientePorId(Integer id) throws Exception {
        ValidationsUtil.integerIsNullOrLessZero(id, ClienteServiceMessages.ID_INVALIDO);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException(String.format(ClienteServiceMessages.CLIENTE_NO_ENCONTRADO_POR_ID, id)));
    }

    @Override
    public ClienteResponse guardar(CrearClienteRequest crearClienteRequest) throws Exception {

        Cliente cliente = ClienteMapper.crearRequestToDomain(crearClienteRequest);
        cliente.setTipoDocumento(TipoDocumentoMapper.dtoToDomain(
                                    tipoDocumentoService.buscarPorId(crearClienteRequest.getTipoDocumentoId())));
        return ClienteMapper.domainToResponse(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponse actualizar(ActualizarClienteRequest actualizarClienteRequest) throws Exception {

        Cliente cliente = buscarClientePorId(actualizarClienteRequest.getId());

        cliente.setTipoDocumento(TipoDocumentoMapper.dtoToDomain(
                                    tipoDocumentoService.buscarPorId(actualizarClienteRequest.getTipoDocumentoId())));
        cliente.setApellidos(actualizarClienteRequest.getApellidos());
        cliente.setDocumento(actualizarClienteRequest.getDocumento());
        cliente.setNombres(actualizarClienteRequest.getNombres());
        cliente.setEstado(actualizarClienteRequest.getEstado());

        return ClienteMapper.domainToResponse(clienteRepository.save(cliente));
    }
}
