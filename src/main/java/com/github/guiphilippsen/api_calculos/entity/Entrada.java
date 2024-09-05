package com.github.guiphilippsen.api_calculos.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Entrada {

    private List<Integer> lista;

}
