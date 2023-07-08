package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.dto.ProductoDTO;
import co.edu.usbcali.market.request.ActualizarProductoRequest;
import co.edu.usbcali.market.request.CrearProductoRequest;
import co.edu.usbcali.market.response.ProductoResponse;
import co.edu.usbcali.market.service.impl.ProductoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoServiceImpl productoService;

    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/buscarTodos")
    List<ProductoResponse> buscarTodos(){
        return productoService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<ProductoResponse>(productoService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    ProductoResponse newProducto(@RequestBody @Valid CrearProductoRequest crearProductoRequest) throws Exception {
        return productoService.guardar(crearProductoRequest);
    }

    @PutMapping("/actualizar")
    ProductoResponse updatedProducto(@RequestBody @Valid ActualizarProductoRequest actualizarProductoRequest) throws Exception {
        return productoService.actualizar(actualizarProductoRequest);
    }
}
