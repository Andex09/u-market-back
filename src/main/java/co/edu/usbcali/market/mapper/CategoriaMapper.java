package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.dto.CategoriaDTO;
import co.edu.usbcali.market.request.ActualizarCategoriaRequest;
import co.edu.usbcali.market.request.CrearCategoriaRequest;
import co.edu.usbcali.market.response.CategoriaResponse;

import java.util.List;

public class CategoriaMapper {

    public static CategoriaDTO domainToDto(Categoria categoria){
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    public static Categoria dtoToDomain(CategoriaDTO categoriaDTO){
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nombre(categoriaDTO.getNombre())
                .descripcion(categoriaDTO.getDescripcion())
                .build();
    }

    public static List<CategoriaDTO> domainToDtoList(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaMapper::domainToDto).toList();
    }

    public static List<CategoriaResponse> domainToResponseList(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaMapper::domainToResponse).toList();
    }

    public static List<Categoria> dtoToDomainList(List<CategoriaDTO> categoriasDtos) {
        return categoriasDtos.stream().map(CategoriaMapper::dtoToDomain).toList();
    }

    public static Categoria crearRequestToDomain(CrearCategoriaRequest crearCategoriaRequest){
        return Categoria.builder()
                .nombre(crearCategoriaRequest.getNombre())
                .descripcion(crearCategoriaRequest.getDescripcion())
                .build();
    }

    public static Categoria actualizarRequestToDomain(ActualizarCategoriaRequest actualizarCategoriaRequest){
        return Categoria.builder()
                .id(actualizarCategoriaRequest.getId())
                .nombre(actualizarCategoriaRequest.getNombre())
                .descripcion(actualizarCategoriaRequest.getDescripcion())
                .build();
    }

    public static CategoriaResponse domainToResponse(Categoria categoria){
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
