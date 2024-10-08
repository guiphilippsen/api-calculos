package com.github.guiphilippsen.api_calculos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int soma;
    private double media;
    private int multiplicar;
    private int moda;
    private double mediana;
    private int menorNumeroLista;
    private int maiorNumeroLista;
    private int totalElementosLista;

}
