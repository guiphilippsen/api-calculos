package com.github.guiphilippsen.api_calculos.service;

import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.repository.CalculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalculosService {
    private CalculoRepository calculoRepository;

    public List<Resultado> findAll(){
        return this.calculoRepository.findAll();
    }

    public Resultado calcular(Entrada entrada) {

        //Cria o objeto resultado
        Resultado resultado = new Resultado();

        //Chama a Função Somar e Salva o Resultado
        int soma = this.soma(entrada.getLista());
        resultado.setSoma(soma);

        //Chama a Função Média e Salva o Resultado
        double media = this.media(entrada.getLista());
        resultado.setMedia(media);

        resultado = this.calculoRepository.save(resultado);

        return resultado;
    }

    //Lógica Soma
    public int soma(List<Integer> lista) {
        int soma = 0;
        for(int i=0; i<lista.size(); i++) {
            soma += lista.get(i);
        }
        return soma;
    }

    //Lógica Média
    public double media(List<Integer> lista) {
        return (double) this.soma(lista) / lista.size();
    }

    public int multiplicacao(List<Integer> lista) {

    }
}
