package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.domain.Pedido;
import co.edu.usbcali.market.dto.PedidoDTO;
import co.edu.usbcali.market.repository.PedidoRepository;
import co.edu.usbcali.market.request.ActualizarPedidoRequest;
import co.edu.usbcali.market.request.CrearPedidoRequest;
import co.edu.usbcali.market.response.PedidoResponse;
import co.edu.usbcali.market.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    public PedidoController(PedidoRepository pedidoRepository, PedidoService pedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/buscarTodos")
    List<PedidoResponse> buscarTodos(){
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<PedidoResponse>(pedidoService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    PedidoResponse newPedido(@RequestBody @Valid CrearPedidoRequest crearPedidoRequest) throws Exception {
        return pedidoService.guardar(crearPedidoRequest);
    }

    @PutMapping("/actualizar")
    PedidoResponse updatedPedido(@RequestBody ActualizarPedidoRequest actualizarPedidoRequest) throws Exception {
        return pedidoService.actualizar(actualizarPedidoRequest);
    }
}
