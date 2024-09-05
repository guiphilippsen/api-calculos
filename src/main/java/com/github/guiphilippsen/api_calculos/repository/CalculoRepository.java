package com.github.guiphilippsen.api_calculos.repository;

import com.github.guiphilippsen.api_calculos.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculoRepository extends JpaRepository<Resultado, Long> {
}
