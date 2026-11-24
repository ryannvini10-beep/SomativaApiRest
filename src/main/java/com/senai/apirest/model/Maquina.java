package com.senai.apirest.model;

import java.time.LocalDateTime;

public class Maquina {

    private Long id;
    private String tipo;
    private double consumoEnergiaKw;
    private LocalDateTime horarioAcionamento;
    private int quantidadePecasProduzidas;

    public Maquina() {}

    public Maquina(Long id, String tipo, double consumoEnergiaKw, LocalDateTime horarioAcionamento, int quantidadePecasProduzidas) {
        this.id = id;
        this.tipo = tipo;
        this.consumoEnergiaKw = consumoEnergiaKw;
        this.horarioAcionamento = horarioAcionamento;
        this.quantidadePecasProduzidas = quantidadePecasProduzidas;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getConsumoEnergiaKw() { return consumoEnergiaKw; }
    public void setConsumoEnergiaKw(double consumoEnergiaKw) { this.consumoEnergiaKw = consumoEnergiaKw; }

    public LocalDateTime getHorarioAcionamento() { return horarioAcionamento; }
    public void setHorarioAcionamento(LocalDateTime horarioAcionamento) { this.horarioAcionamento = horarioAcionamento; }

    public int getQuantidadePecasProduzidas() { return quantidadePecasProduzidas; }
    public void setQuantidadePecasProduzidas(int quantidadePecasProduzidas) { this.quantidadePecasProduzidas = quantidadePecasProduzidas; }
}