package app.controllertest;

import com.github.guiphilippsen.api_calculos.controller.CalculosController;
import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.service.CalculosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalculosControllerTest {

    @InjectMocks
    private CalculosController calculosController;

    @Mock
    private CalculosService calculosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste de sucesso no cálculo")
    public void calcular_Sucesso() {
        Entrada entrada = new Entrada();  // Criar a entrada com valores adequados
        Resultado resultadoEsperado = new Resultado();  // Criar o resultado esperado

        when(calculosService.calcular(entrada)).thenReturn(resultadoEsperado);

        ResponseEntity<Resultado> response = calculosController.calcular(entrada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultadoEsperado, response.getBody());
        verify(calculosService, times(1)).calcular(entrada);
    }

    @Test
    @DisplayName("Teste de erro no cálculo")
    public void calcular_Erro() {
        Entrada entrada = new Entrada();  // Criar a entrada com valores inadequados

        when(calculosService.calcular(entrada)).thenThrow(new RuntimeException());

        ResponseEntity<Resultado> response = calculosController.calcular(entrada);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(calculosService, times(1)).calcular(entrada);
    }

    @Test
    @DisplayName("Teste para buscar todos os resultados com sucesso")
    public void findAll_Sucesso() {
        List<Resultado> listaEsperada = Arrays.asList(new Resultado(), new Resultado());

        when(calculosService.findAll()).thenReturn(listaEsperada);

        ResponseEntity<List<Resultado>> response = calculosController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaEsperada, response.getBody());
        verify(calculosService, times(1)).findAll();
    }

    @Test
    @DisplayName("Teste de erro ao buscar todos os resultados")
    public void findAll_Erro() {
        when(calculosService.findAll()).thenThrow(new RuntimeException());

        ResponseEntity<List<Resultado>> response = calculosController.findAll();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(calculosService, times(1)).findAll();
    }
}
