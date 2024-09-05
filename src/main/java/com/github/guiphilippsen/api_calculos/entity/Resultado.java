package com.github.guiphilippsen.api_calculos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int soma;
    private double media;
    private int multiplicar;
    private int moda;
    private int mediana;

    private int menorNumeroLista;
    private int maiorNumeroLista;
    private int totalElementosLista;

}
