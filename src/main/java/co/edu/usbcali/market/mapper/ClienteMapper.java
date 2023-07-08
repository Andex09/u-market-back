package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.Cliente;
import co.edu.usbcali.market.dto.ClienteDTO;
import co.edu.usbcali.market.request.ActualizarClienteRequest;
import co.edu.usbcali.market.request.CrearClienteRequest;
import co.edu.usbcali.market.response.ClienteResponse;

import java.util.List;

public class ClienteMapper {

    public static ClienteDTO domainToDto(Cliente cliente){
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .documento(cliente.getDocumento())
                .estado(cliente.getEstado())
                .tipoDocumentoId((cliente.getTipoDocumento() == null?
                        null : cliente.getTipoDocumento().getId()))
                .build();
    }

    public static Cliente dtoToDomain(ClienteDTO clienteDto){
        return Cliente.builder()
                .id(clienteDto.getId())
                .nombres(clienteDto.getNombres())
                .apellidos(clienteDto.getApellidos())
                .documento(clienteDto.getDocumento())
                .estado(clienteDto.getEstado())
                .build();
    }

    public static List<ClienteDTO> domainToDtoList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::domainToDto).toList();
    }

    public static List<ClienteResponse> domainToResponseList(List<Cliente> clientes) {
        return clientes.stream().map(ClienteMapper::domainToResponse).toList();
    }

    public static List<Cliente> dtoToDomainList(List<ClienteDTO> clientesDtos) {
        return clientesDtos.stream().map(ClienteMapper::dtoToDomain).toList();
    }

    public static Cliente crearRequestToDomain(CrearClienteRequest crearClienteRequest){
        return Cliente.builder()
                .nombres(crearClienteRequest.getNombres())
                .apellidos(crearClienteRequest.getApellidos())
                .estado(crearClienteRequest.getEstado())
                .documento(crearClienteRequest.getDocumento())
                .build();
    }

    public static Cliente actualizarRequestToDomain(ActualizarClienteRequest actualizarClienteRequest){
        return Cliente.builder()
                .id(actualizarClienteRequest.getId())
                .nombres(actualizarClienteRequest.getNombres())
                .apellidos(actualizarClienteRequest.getApellidos())
                .estado(actualizarClienteRequest.getEstado())
                .documento(actualizarClienteRequest.getDocumento())
                .build();
    }

    public static ClienteResponse domainToResponse(Cliente cliente){
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombres(cliente.getNombres())
                .apellidos(cliente.getApellidos())
                .estado(cliente.getEstado())
                .documento(cliente.getDocumento())
                .tipoDocumentoDescripcion(cliente.getTipoDocumento().getDescripcion())
                .tipoDocumentoId(cliente.getTipoDocumento().getId())
                .build();
    }
}
