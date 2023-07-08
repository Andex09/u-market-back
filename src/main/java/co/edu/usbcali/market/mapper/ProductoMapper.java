package co.edu.usbcali.market.mapper;

import co.edu.usbcali.market.domain.Producto;
import co.edu.usbcali.market.dto.ProductoDTO;
import co.edu.usbcali.market.request.ActualizarProductoRequest;
import co.edu.usbcali.market.request.CrearProductoRequest;
import co.edu.usbcali.market.response.ProductoResponse;

import java.util.List;

public class ProductoMapper {

    public static ProductoDTO domainToDto(Producto producto){
        return ProductoDTO.builder()
                .id(producto.getId())
                .referencia(producto.getReferencia())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .unidadesDisponibles(producto.getUnidadesDisponibles())
                .precioUnitario(producto.getPrecioUnitario())
                .categoriaId(producto.getCategoria() == null ?
                            null : producto.getCategoria().getId())
                .build();
    }

    public static Producto dtoToDomain(ProductoDTO productoDto){
        return Producto.builder()
                .id(productoDto.getId())
                .referencia(productoDto.getReferencia())
                .nombre(productoDto.getNombre())
                .descripcion(productoDto.getDescripcion())
                .unidadesDisponibles(productoDto.getUnidadesDisponibles())
                .precioUnitario(productoDto.getPrecioUnitario())
                .build();
    }

    public static List<ProductoDTO> domainToDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::domainToDto).toList();
    }

    public static List<ProductoResponse> domainToResponseList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::domainToResponse).toList();
    }

    public static List<Producto> dtoToDomainList(List<ProductoDTO> productosDtos) {
        return productosDtos.stream().map(ProductoMapper::dtoToDomain).toList();
    }

    public static Producto crearRequestToDomain(CrearProductoRequest crearProductoRequest){
        return Producto.builder()
                .referencia(crearProductoRequest.getReferencia())
                .nombre(crearProductoRequest.getNombre())
                .descripcion(crearProductoRequest.getDescripcion())
                .unidadesDisponibles(crearProductoRequest.getUnidadesDisponibles())
                .precioUnitario(crearProductoRequest.getPrecioUnitario())
                .build();
    }

    public static Producto actualizarRequestToDomain(ActualizarProductoRequest actualizarProductoRequest){
        return Producto.builder()
                .id(actualizarProductoRequest.getId())
                .referencia(actualizarProductoRequest.getReferencia())
                .nombre(actualizarProductoRequest.getNombre())
                .descripcion(actualizarProductoRequest.getDescripcion())
                .unidadesDisponibles(actualizarProductoRequest.getUnidadesDisponibles())
                .precioUnitario(actualizarProductoRequest.getPrecioUnitario())
                .build();
    }

    public static ProductoResponse domainToResponse(Producto producto){
        return ProductoResponse.builder()
                .id(producto.getId())
                .referencia(producto.getReferencia())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .unidadesDisponibles(producto.getUnidadesDisponibles())
                .precioUnitario(producto.getPrecioUnitario())
                .nombreCategoria(producto.getCategoria() == null ?
                        null : producto.getCategoria().getNombre())
                .categoriaId(producto.getCategoria() == null ?
                        null : producto.getCategoria().getId())
                .build();
    }
}
