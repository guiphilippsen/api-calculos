package com.github.guiphilippsen.api_calculos.service;

import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.repository.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculosService {

    @Autowired
    private CalculoRepository calculoRepository;

    public List<Resultado> findAll(){
        return this.calculoRepository.findAll();
    }

    public Resultado calcular(Entrada entrada) {

        //Objeto resultado
        Resultado resultado = new Resultado();

        //Soma
        int soma = this.somar(entrada.getLista());
        resultado.setSoma(soma);

        //Média
        double media = this.media(entrada.getLista());
        resultado.setMedia(media);

        //Multiplicação
        int multiplicar = this.multiplicacao(entrada.getLista());
        resultado.setMultiplicar(multiplicar);

        //Maior Número da Lista
        int maiorNumeroLista = this.maiorNumero(entrada.getLista());
        resultado.setMaiorNumeroLista(maiorNumeroLista);

        //Menor Número da Lista
        int menorNumeroLista = this.menorNumero(entrada.getLista());
        resultado.setMenorNumeroLista(menorNumeroLista);

        //Moda
        int moda = this.modaL(entrada.getLista());
        resultado.setModa(moda);

        //Mediana
        double mediana = this.mediana(entrada.getLista());
        resultado.setMediana(mediana);

        int tamanhoLista = this.tamanhoLista(entrada.getLista());
        resultado.setTotalElementosLista(tamanhoLista);

        resultado = this.calculoRepository.save(resultado);

        return resultado;
    }

    //Lógica Soma
    public int somar(List<Integer> lista) {
        int soma = 0;
        for(int i=0; i<lista.size(); i++) {
            soma += lista.get(i);
        }
        return soma;
    }

    // Lógica Média
    public double media(List<Integer> lista) {
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        return (double) this.somar(lista) / lista.size();
    }

    //Lógica Multiplicação
    public int multiplicacao(List<Integer> lista) {
        int mult = 1;
        for(int i=0; i< lista.size(); i++) {
            mult *= lista.get(i);
        }
        return mult;
    }

    //Lógica Maior Numero da Lista
    public int maiorNumero(List<Integer> lista) {
        return Collections.max(lista);
    }

    //Lógica Menor Numero da Lista
    public int menorNumero(List<Integer> lista) {
        return Collections.min(lista);
    }

    //Lógica Moda
    public int modaL(List<Integer> lista) {
        Map<Integer, Integer> frequencia = new HashMap<>();
        int moda = lista.get(0);
        int maxFrequencia = 0;

        for (int numero : lista) {
            int freq = frequencia.getOrDefault(numero, 0) + 1;
            frequencia.put(numero, freq);

            if (freq > maxFrequencia) {
                maxFrequencia = freq;
                moda = numero;
            }
        }
        return moda;
    }

    //Lógica Mediana
    public double mediana(List<Integer> lista) {
        Collections.sort(lista);
        int tamanho = lista.size();

        if (tamanho % 2 == 1) {
            return lista.get(tamanho / 2);
        }else {
            int metade1 = lista.get(tamanho / 2 - 1);
            int metade2 = lista.get(tamanho / 2);
            return (metade1 + metade2) / 2.0;
        }
    }
    // Lógica Tamanho da Lista
    public int tamanhoLista(List<Integer> lista) {
        return lista.size();
    }
}
