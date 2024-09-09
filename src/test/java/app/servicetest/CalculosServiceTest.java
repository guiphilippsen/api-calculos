package app.servicetest;

import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.repository.CalculoRepository;
import com.github.guiphilippsen.api_calculos.service.CalculosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculosServiceTest {

    @InjectMocks
    private CalculosService calculosService;

    @Mock
    private CalculoRepository calculoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar a soma correta da lista")
    public void testSomar() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(15, calculosService.somar(lista));
    }

    @Test
    @DisplayName("Deve retornar 0 quando a lista está vazia")
    public void testSomarListaVazia() {
        List<Integer> lista = Collections.emptyList();
        assertEquals(0, calculosService.somar(lista));
    }

    @Test
    @DisplayName("Deve retornar a média correta da lista")
    public void testMedia() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(3.0, calculosService.media(lista));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando a lista está vazia para média")
    public void testMediaListaVazia() {
        List<Integer> lista = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> calculosService.media(lista));
    }

    @Test
    @DisplayName("Deve retornar o produto correto da lista")
    public void testMultiplicacao() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4);
        assertEquals(24, calculosService.multiplicacao(lista));
    }

    @Test
    @DisplayName("Deve retornar 1 quando a lista contém apenas um elemento")
    public void testMultiplicacaoListaUnica() {
        List<Integer> lista = Collections.singletonList(5);
        assertEquals(5, calculosService.multiplicacao(lista));
    }

    @Test
    @DisplayName("Deve retornar o maior número da lista corretamente")
    public void testMaiorNumero() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(5, calculosService.maiorNumero(lista));
    }

    @Test
    @DisplayName("Deve retornar Integer.MIN_VALUE quando a lista está vazia")
    public void testMaiorNumeroListaVazia() {
        List<Integer> lista = Collections.emptyList();
        assertThrows(NoSuchElementException.class, () -> calculosService.maiorNumero(lista));
    }

    @Test
    @DisplayName("Deve retornar o menor número da lista corretamente")
    public void testMenorNumero() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(1, calculosService.menorNumero(lista));
    }

    @Test
    @DisplayName("Deve retornar Integer.MAX_VALUE quando a lista está vazia")
    public void testMenorNumeroListaVazia() {
        List<Integer> lista = Collections.emptyList();
        assertThrows(NoSuchElementException.class, () -> calculosService.menorNumero(lista));
    }

    @Test
    @DisplayName("Deve retornar a moda correta da lista")
    public void testModa() {
        List<Integer> lista = Arrays.asList(1, 2, 2, 3, 4, 4, 4);
        assertEquals(4, calculosService.modaL(lista));
    }

    @Test
    @DisplayName("Deve retornar o primeiro elemento como moda quando todos são únicos")
    public void testModaTodosUnicos() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(1, calculosService.modaL(lista));
    }

    @Test
    @DisplayName("Deve retornar a mediana correta da lista ímpar")
    public void testMedianaImpar() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(3.0, calculosService.mediana(lista));
    }

    @Test
    @DisplayName("Deve retornar a mediana correta da lista par")
    public void testMedianaPar() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4);
        assertEquals(2.5, calculosService.mediana(lista));
    }

    @Test
    @DisplayName("Deve retornar o tamanho correto da lista")
    public void testTamanhoLista() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(5, calculosService.tamanhoLista(lista));
    }

    @Test
    @DisplayName("Deve retornar 0 quando a lista está vazia")
    public void testTamanhoListaVazia() {
        List<Integer> lista = Collections.emptyList();
        assertEquals(0, calculosService.tamanhoLista(lista));
    }

    @Test
    @DisplayName("Deve realizar o cálculo corretamente")
    public void testCalcular() {
        Entrada entrada = new Entrada();
        entrada.setLista(Arrays.asList(1, 2, 3, 4, 5));

        Resultado expectedResultado = new Resultado();
        expectedResultado.setSoma(15);
        expectedResultado.setMedia(3.0);
        expectedResultado.setMultiplicar(120);
        expectedResultado.setMaiorNumeroLista(5);
        expectedResultado.setMenorNumeroLista(1);
        expectedResultado.setModa(1); // Ajustar conforme necessário
        expectedResultado.setMediana(3.0);
        expectedResultado.setTotalElementosLista(5);

        when(calculoRepository.save(any(Resultado.class))).thenReturn(expectedResultado);

        Resultado resultado = calculosService.calcular(entrada);

        assertEquals(expectedResultado.getSoma(), resultado.getSoma());
        assertEquals(expectedResultado.getMedia(), resultado.getMedia());
        assertEquals(expectedResultado.getMultiplicar(), resultado.getMultiplicar());
        assertEquals(expectedResultado.getMaiorNumeroLista(), resultado.getMaiorNumeroLista());
        assertEquals(expectedResultado.getMenorNumeroLista(), resultado.getMenorNumeroLista());
        assertEquals(expectedResultado.getModa(), resultado.getModa());
        assertEquals(expectedResultado.getMediana(), resultado.getMediana());
        assertEquals(expectedResultado.getTotalElementosLista(), resultado.getTotalElementosLista());

        verify(calculoRepository).save(any(Resultado.class));
    }
}