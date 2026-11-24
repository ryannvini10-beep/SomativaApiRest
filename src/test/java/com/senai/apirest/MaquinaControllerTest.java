package com.senai.apirest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.senai.apirest.controller.MaquinaController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MaquinaController.class)
public class MaquinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveListarMaquinasComSucesso() throws Exception {
        mockMvc.perform(get("/api/maquinas"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveCriarMaquinaComSucesso() throws Exception {
        String jsonBody = "{" +
                "\"tipo\": \"Torno CNC\"," +
                "\"consumoEnergiaKw\": 15.5," +
                "\"horarioAcionamento\": \"2026-11-23T08:00:00\"," +
                "\"quantidadePecasProduzidas\": 120" +
                "}";

        mockMvc.perform(post("/api/maquinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isOk());
    }
}