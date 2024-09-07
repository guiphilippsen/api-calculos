package com.github.guiphilippsen.api_calculos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private List<Integer> lista;

}
