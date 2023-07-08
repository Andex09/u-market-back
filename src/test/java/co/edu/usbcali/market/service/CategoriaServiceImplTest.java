package co.edu.usbcali.market.service;

import co.edu.usbcali.market.domain.Categoria;
import co.edu.usbcali.market.mocks.CategoriaMockTest;
import co.edu.usbcali.market.repository.CategoriaRepository;
import co.edu.usbcali.market.request.CrearCategoriaRequest;
import co.edu.usbcali.market.response.CategoriaResponse;
import co.edu.usbcali.market.service.impl.CategoriaServiceImpl;
import co.edu.usbcali.market.util.Message.CategoriaServiceMessages;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CategoriaServiceImplTest {
    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;


    @Test
    void obtenerTodasLasCategorias_OK() {
        given(categoriaRepository.findAllByOrderByNombreAsc()).willReturn(
                CategoriaMockTest.CATEGORIAS_LIST
        );

        List<CategoriaResponse> obtenerTodas = categoriaService.obtenerTodos();

        assertEquals(2, obtenerTodas.size());
        assertEquals(CategoriaMockTest.ID_UNO, obtenerTodas.get(0).getId());
    }

    @Test
    void buscarPorId_NOK() {
        Exception exception = assertThrows(Exception.class, () ->
                categoriaService.buscarPorId(2));

        String expectedMessage = String.format(
                CategoriaServiceMessages.CATEGORIA_NO_ENCONTRADO_POR_ID, 2);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void buscarPorId_OK() throws Exception {
        given(categoriaRepository.findById(1)).willReturn(
                Optional.of(CategoriaMockTest.CATEGORIA_UNO)
        );

        CategoriaResponse categoriaResponse = categoriaService.buscarPorId(1);

        assertNotNull(categoriaResponse);
        assertEquals(CategoriaMockTest.NOMBRE_UNO, categoriaResponse.getNombre());
    }

    @Test
    void guardarOk() throws Exception{
        CrearCategoriaRequest catToSave = CrearCategoriaRequest.builder()
                .nombre("C2").descripcion("C2")
                .build();

        given(categoriaRepository.save(any(Categoria.class)))
                .willReturn(Categoria.builder().id(2).nombre("C2").descripcion("C2").build());
        CategoriaResponse categoriaResult = categoriaService.guardar(catToSave);
        assertNotNull(categoriaResult);
        assertEquals(2, categoriaResult.getId());
    }

    @Test
    void guardarError() {

        given(categoriaRepository.existsByNombreIgnoreCase(
                CategoriaMockTest.NOMBRE_UNO
        )).willReturn(true);

        Exception exception= assertThrows(Exception.class, ()->categoriaService.guardar(CategoriaMockTest.CREAR_CATEGORIA_REQUEST_UNO));

        String message = String.format(CategoriaServiceMessages.EXISTE_POR_NOMBRE, CategoriaMockTest.NOMBRE_UNO);

        assertEquals(message, exception.getMessage());
    }
}
