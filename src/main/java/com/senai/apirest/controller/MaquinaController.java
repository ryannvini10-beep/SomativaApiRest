package com.senai.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.apirest.model.Maquina;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    // Lista em memória para simular a base de dados
    private static final List<Maquina> listaMaquinas = new ArrayList<>();
    private static Long idCounter = 1L;

    @GetMapping
    public List<Maquina> listar() {
        return listaMaquinas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> buscarPorId(@PathVariable Long id) {
        Optional<Maquina> maquina = listaMaquinas.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        return maquina.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Maquina criar(@RequestBody Maquina maquina) {
        maquina.setId(idCounter++);
        listaMaquinas.add(maquina);
        return maquina;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maquina> atualizar(@PathVariable Long id, @RequestBody Maquina maquinaAtualizada) {
        Optional<Maquina> maquinaExistente = listaMaquinas.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        if (maquinaExistente.isPresent()) {
            Maquina m = maquinaExistente.get();
            m.setTipo(maquinaAtualizada.getTipo());
            m.setConsumoEnergiaKw(maquinaAtualizada.getConsumoEnergiaKw());
            m.setHorarioAcionamento(maquinaAtualizada.getHorarioAcionamento());
            m.setQuantidadePecasProduzidas(maquinaAtualizada.getQuantidadePecasProduzidas());
            return ResponseEntity.ok(m);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = listaMaquinas.removeIf(m -> m.getId().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}