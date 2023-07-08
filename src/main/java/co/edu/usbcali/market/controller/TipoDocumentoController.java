package co.edu.usbcali.market.controller;

import co.edu.usbcali.market.dto.TipoDocumentoDTO;
import co.edu.usbcali.market.mapper.TipoDocumentoMapper;
import co.edu.usbcali.market.repository.TipoDocumentoRepository;
import co.edu.usbcali.market.service.TipoDocumentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoDocumento")
@CrossOrigin("*")
public class TipoDocumentoController {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoDocumentoService tipoDocumentoService;
    public TipoDocumentoController(TipoDocumentoRepository tipoDocumentoRepository, TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/buscarTodos")
    List<TipoDocumentoDTO> buscarTodos(){
        return TipoDocumentoMapper.domainToDtoList(tipoDocumentoRepository.findAll());
    }

    @GetMapping("/buscarPorId/")
    ResponseEntity<?> buscarPorId(@RequestParam("id") Integer id) throws Exception {
        try {
            return new ResponseEntity<TipoDocumentoDTO>(tipoDocumentoService.buscarPorId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
