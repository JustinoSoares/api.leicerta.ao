package ao.leicerta.api.controller;

import ao.leicerta.api.model.BuscaRequest;
import ao.leicerta.api.model.BuscaResponse;
import ao.leicerta.api.model.TipoDocumento;
import ao.leicerta.api.service.BuscaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BuscaController.class)
class BuscaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BuscaService buscaService;

    @Test
    void buscarPost_deveRetornarResultados() throws Exception {
        BuscaResponse response = BuscaResponse.builder()
                .consulta("trabalho")
                .totalResultados(1)
                .resultados(List.of(
                        BuscaResponse.ResultadoBusca.builder()
                                .legislacaoId(2L)
                                .legislacaoTitulo("Lei Geral do Trabalho")
                                .tipo(TipoDocumento.LEI)
                                .artigoNumero(14)
                                .artigoTitulo("Contrato de Trabalho")
                                .trecho("...contrato de trabalho...")
                                .relevancia(0.8)
                                .build()
                ))
                .build();

        when(buscaService.buscar(any())).thenReturn(response);

        BuscaRequest request = new BuscaRequest();
        request.setConsulta("trabalho");

        mockMvc.perform(post("/api/v1/busca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consulta").value("trabalho"))
                .andExpect(jsonPath("$.totalResultados").value(1))
                .andExpect(jsonPath("$.resultados[0].legislacaoTitulo").value("Lei Geral do Trabalho"));
    }

    @Test
    void buscarPost_semConsulta_deveRetornarBadRequest() throws Exception {
        BuscaRequest requestVazia = new BuscaRequest();

        mockMvc.perform(post("/api/v1/busca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestVazia)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarGet_deveRetornarResultados() throws Exception {
        BuscaResponse response = BuscaResponse.builder()
                .consulta("constituicao")
                .totalResultados(0)
                .resultados(List.of())
                .build();

        when(buscaService.buscar(any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/busca?q=constituicao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consulta").value("constituicao"));
    }
}
