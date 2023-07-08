package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.dto.ClienteDTO;
import co.edu.usbcali.market.request.ActualizarClienteRequest;
import co.edu.usbcali.market.request.CrearClienteRequest;
import co.edu.usbcali.market.response.ClienteResponse;
import co.edu.usbcali.market.service.impl.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {
    private final ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/buscarTodos")
    List<ClienteResponse> buscarTodos(){
        return clienteService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<ClienteResponse>(clienteService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    ClienteResponse newCliente(@RequestBody @Valid CrearClienteRequest crearClienteRequest) throws Exception {
        return clienteService.guardar(crearClienteRequest);
    }

    @PutMapping("/actualizar")
    ClienteResponse updatedCliente(@RequestBody @Valid ActualizarClienteRequest actualizarClienteRequest) throws Exception {
        return clienteService.actualizar(actualizarClienteRequest);
    }
}
