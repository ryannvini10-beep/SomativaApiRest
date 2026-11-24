package com.senai.apirest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static final List<Maquina> listaMaquinas = new ArrayList<>();
    private static Long idCounter = 1L;

    // Endpoints globais do sistema
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Bem-vindo ao sistema de monitoramento de máquinas industriais - CloudTech Solutions");
    }

    @GetMapping("/status")
    public ResponseEntity status() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("status", "online");
        resposta.put("mensagem", "Aplicação de monitoramento em execução");
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/versao")
    public ResponseEntity versao() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("versao", "1.0.0");
        resposta.put("ambiente", "Produção / Railway");
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/documentacao")
    public ResponseEntity documentacao() {
        Map<String, String> doc = new HashMap<>();
        doc.put("rota_home", "GET /api/maquinas/");
        doc.put("rota_status", "GET /api/maquinas/status");
        doc.put("rota_versao", "GET /api/maquinas/versao");
        doc.put("rota_listar", "GET /api/maquinas");
        doc.put("rota_cadastrar", "POST /api/maquinas");
        doc.put("rota_atualizar", "PUT /api/maquinas/{id}");
        doc.put("rota_deletar", "DELETE /api/maquinas/{id}");
        return ResponseEntity.ok(doc);
    }

    // CRUD de Máquinas
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
    public ResponseEntity<Maquina> criar(@RequestBody Maquina maquina) {
        maquina.setId(idCounter++);
        listaMaquinas.add(maquina);
        return ResponseEntity.status(201).body(maquina);
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