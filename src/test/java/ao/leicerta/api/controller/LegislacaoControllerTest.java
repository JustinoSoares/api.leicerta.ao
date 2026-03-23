package ao.leicerta.api.controller;

import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.model.TipoDocumento;
import ao.leicerta.api.service.LegislacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LegislacaoController.class)
class LegislacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LegislacaoService legislacaoService;

    private Legislacao criarLegislacaoExemplo() {
        return Legislacao.builder()
                .id(1L)
                .titulo("Constituição da República de Angola")
                .descricao("Lei fundamental do Estado")
                .tipo(TipoDocumento.CONSTITUICAO)
                .dataPublicacao(LocalDate.of(2010, 2, 5))
                .build();
    }

    @Test
    void listarTodas_deveRetornarListaDeLegislacao() throws Exception {
        when(legislacaoService.listarTodas()).thenReturn(List.of(criarLegislacaoExemplo()));

        mockMvc.perform(get("/api/v1/legislacao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Constituição da República de Angola"))
                .andExpect(jsonPath("$[0].tipo").value("CONSTITUICAO"));
    }

    @Test
    void listarPorTipo_deveRetornarFiltrado() throws Exception {
        when(legislacaoService.listarPorTipo(TipoDocumento.CONSTITUICAO))
                .thenReturn(List.of(criarLegislacaoExemplo()));

        mockMvc.perform(get("/api/v1/legislacao?tipo=CONSTITUICAO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("CONSTITUICAO"));
    }

    @Test
    void buscarPorId_deveRetornarLegislacao() throws Exception {
        when(legislacaoService.buscarPorId(1L)).thenReturn(criarLegislacaoExemplo());

        mockMvc.perform(get("/api/v1/legislacao/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Constituição da República de Angola"));
    }

    @Test
    void criar_comDadosValidos_deveRetornarCreated() throws Exception {
        Legislacao nova = Legislacao.builder()
                .titulo("Nova Lei de Teste")
                .tipo(TipoDocumento.LEI)
                .build();
        Legislacao salva = Legislacao.builder()
                .id(10L)
                .titulo("Nova Lei de Teste")
                .tipo(TipoDocumento.LEI)
                .build();

        when(legislacaoService.salvar(any())).thenReturn(salva);

        mockMvc.perform(post("/api/v1/legislacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nova)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    void criar_semTitulo_deveRetornarBadRequest() throws Exception {
        Legislacao semTitulo = Legislacao.builder()
                .tipo(TipoDocumento.LEI)
                .build();

        mockMvc.perform(post("/api/v1/legislacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(semTitulo)))
                .andExpect(status().isBadRequest());
    }
}
