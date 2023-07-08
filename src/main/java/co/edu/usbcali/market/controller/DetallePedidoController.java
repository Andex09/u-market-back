package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.dto.DetallePedidoDTO;
import co.edu.usbcali.market.request.ActualizarDetallePedidoRequest;
import co.edu.usbcali.market.request.CrearDetallePedidoRequest;
import co.edu.usbcali.market.response.DetallePedidoResponse;
import co.edu.usbcali.market.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallePedido")
@CrossOrigin("*")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/buscarTodos")
    List<DetallePedidoResponse> buscarTodos(){
        return detallePedidoService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<DetallePedidoResponse>(detallePedidoService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    DetallePedidoResponse newDetallePedido(@RequestBody @Valid CrearDetallePedidoRequest crearDetallePedidoRequest) throws Exception {
        return detallePedidoService.guardar(crearDetallePedidoRequest);
    }

    @PutMapping("/actualizar")
    DetallePedidoResponse updatedDetallePedido(@RequestBody @Valid ActualizarDetallePedidoRequest actualizarDetallePedidoRequest) throws Exception {
        return detallePedidoService.actualizar(actualizarDetallePedidoRequest);
    }
}
