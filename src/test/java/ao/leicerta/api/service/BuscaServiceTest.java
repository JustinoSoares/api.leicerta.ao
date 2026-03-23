package ao.leicerta.api.service;

import ao.leicerta.api.model.Artigo;
import ao.leicerta.api.model.BuscaRequest;
import ao.leicerta.api.model.BuscaResponse;
import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.model.TipoDocumento;
import ao.leicerta.api.repository.ArtigoRepository;
import ao.leicerta.api.repository.LegislacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaServiceTest {

    @Mock
    private LegislacaoRepository legislacaoRepository;

    @Mock
    private ArtigoRepository artigoRepository;

    @InjectMocks
    private BuscaService buscaService;

    private Legislacao legislacaoExemplo;
    private Artigo artigoExemplo;

    @BeforeEach
    void setUp() {
        legislacaoExemplo = Legislacao.builder()
                .id(2L)
                .titulo("Lei Geral do Trabalho")
                .tipo(TipoDocumento.LEI)
                .build();

        artigoExemplo = Artigo.builder()
                .id(1L)
                .numero(14)
                .titulo("Contrato de Trabalho")
                .conteudo("O contrato de trabalho define a relação entre trabalhador e empregador.")
                .legislacao(legislacaoExemplo)
                .build();
    }

    @Test
    void buscar_comResultados_deveRetornarBuscaResponse() {
        when(artigoRepository.buscarPorTermo(anyString())).thenReturn(List.of(artigoExemplo));

        BuscaRequest request = new BuscaRequest();
        request.setConsulta("trabalho");

        BuscaResponse response = buscaService.buscar(request);

        assertThat(response).isNotNull();
        assertThat(response.getConsulta()).isEqualTo("trabalho");
        assertThat(response.getTotalResultados()).isEqualTo(1);
        assertThat(response.getResultados()).hasSize(1);
        assertThat(response.getResultados().get(0).getLegislacaoTitulo()).isEqualTo("Lei Geral do Trabalho");
    }

    @Test
    void buscar_semResultados_deveRetornarListaVazia() {
        when(artigoRepository.buscarPorTermo(anyString())).thenReturn(List.of());

        BuscaRequest request = new BuscaRequest();
        request.setConsulta("termoInexistente");

        BuscaResponse response = buscaService.buscar(request);

        assertThat(response.getTotalResultados()).isEqualTo(0);
        assertThat(response.getResultados()).isEmpty();
    }

    @Test
    void buscar_comLimite_deveRespeitarLimite() {
        when(artigoRepository.buscarPorTermo(anyString())).thenReturn(List.of(artigoExemplo, artigoExemplo, artigoExemplo));

        BuscaRequest request = new BuscaRequest();
        request.setConsulta("trabalho");
        request.setLimite(2);

        BuscaResponse response = buscaService.buscar(request);

        assertThat(response.getTotalResultados()).isLessThanOrEqualTo(2);
    }

    @Test
    void buscar_comTipo_deveFiltrarPorTipo() {
        when(legislacaoRepository.buscarPorTermoETipo(anyString(), any())).thenReturn(List.of(legislacaoExemplo));
        when(artigoRepository.buscarPorTermo(anyString())).thenReturn(List.of(artigoExemplo));

        BuscaRequest request = new BuscaRequest();
        request.setConsulta("trabalho");
        request.setTipo(TipoDocumento.LEI);

        BuscaResponse response = buscaService.buscar(request);

        assertThat(response).isNotNull();
        assertThat(response.getResultados().get(0).getTipo()).isEqualTo(TipoDocumento.LEI);
    }
}
