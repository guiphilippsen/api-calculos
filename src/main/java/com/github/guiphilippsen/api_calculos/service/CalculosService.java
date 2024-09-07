package com.github.guiphilippsen.api_calculos.service;

import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.repository.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    //Lógica Média
    public double media(List<Integer> lista) {
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
}
