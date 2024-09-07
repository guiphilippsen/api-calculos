package com.github.guiphilippsen.api_calculos.controller;

import com.github.guiphilippsen.api_calculos.entity.Entrada;
import com.github.guiphilippsen.api_calculos.entity.Resultado;
import com.github.guiphilippsen.api_calculos.service.CalculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculos")
public class CalculosController {
    @Autowired
    private CalculosService calculosService;

    @PostMapping("/calcular")
    public ResponseEntity<Resultado> calcular(@RequestBody Entrada entrada){
        try {
            Resultado resultado = this.calculosService.calcular(entrada);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/resultados")
    public ResponseEntity<List<Resultado>> findAll() {
        try {
            List<Resultado> lista = this.calculosService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
