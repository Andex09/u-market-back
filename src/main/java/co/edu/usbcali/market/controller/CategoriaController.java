package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.dto.CategoriaDTO;
import co.edu.usbcali.market.repository.CategoriaRepository;
import co.edu.usbcali.market.request.ActualizarCategoriaRequest;
import co.edu.usbcali.market.request.CrearCategoriaRequest;
import co.edu.usbcali.market.response.CategoriaResponse;
import co.edu.usbcali.market.service.impl.CategoriaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaServiceImpl categoriaService;

    public CategoriaController(CategoriaRepository categoriaRepository, CategoriaServiceImpl categoriaService) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/buscarTodos")
    List<CategoriaResponse> buscarTodos(){
        return categoriaService.obtenerTodos();
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<CategoriaResponse>(categoriaService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    CategoriaResponse newCategoria(@RequestBody @Valid CrearCategoriaRequest crearCategoriaRequest) throws Exception {
        return categoriaService.guardar(crearCategoriaRequest);
    }

    @PutMapping("/actualizar")
    CategoriaResponse updatedCategoria(@RequestBody @Valid ActualizarCategoriaRequest actualizarCategoriaRequest) throws Exception {
        return categoriaService.actualizar(actualizarCategoriaRequest);
    }
}
