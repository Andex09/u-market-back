package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.dto.EstadoPedidoDTO;
import co.edu.usbcali.market.service.EstadoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadoPedido")
@CrossOrigin("*")
public class EstadoPedidoController {

    private final EstadoPedidoService estadoPedidoService;

    public EstadoPedidoController(EstadoPedidoService estadoPedidoService) {
        this.estadoPedidoService = estadoPedidoService;
    }

    @GetMapping("/buscarTodos")
    List<EstadoPedidoDTO> buscarTodos(){
        return estadoPedidoService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<EstadoPedidoDTO>(estadoPedidoService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
