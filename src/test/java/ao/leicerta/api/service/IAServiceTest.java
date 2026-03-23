package ao.leicerta.api.service;

import ao.leicerta.api.model.PerguntaRequest;
import ao.leicerta.api.model.RespostaIA;
import ao.leicerta.api.model.BuscaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IAServiceTest {

    @Mock
    private BuscaService buscaService;

    @InjectMocks
    private IAService iaService;

    @BeforeEach
    void setUp() {
        BuscaResponse buscaResponse = BuscaResponse.builder()
                .consulta("trabalho")
                .totalResultados(0)
                .resultados(List.of())
                .build();
        when(buscaService.buscar(any())).thenReturn(buscaResponse);
    }

    @Test
    void responder_deveRetornarRespostaComAviso() {
        PerguntaRequest request = new PerguntaRequest();
        request.setPergunta("Quais são os meus direitos no trabalho?");

        RespostaIA resposta = iaService.responder(request);

        assertThat(resposta).isNotNull();
        assertThat(resposta.getPergunta()).isEqualTo("Quais são os meus direitos no trabalho?");
        assertThat(resposta.getAviso()).isNotBlank();
        assertThat(resposta.getResposta()).isNotBlank();
    }

    @Test
    void responder_perguntaSobreTrabalho_deveRetornarOrientacaoEspecifica() {
        PerguntaRequest request = new PerguntaRequest();
        request.setPergunta("Tenho direito a férias no trabalho?");

        RespostaIA resposta = iaService.responder(request);

        assertThat(resposta.getOrientacaoPratica()).contains("INEFOP");
    }

    @Test
    void responder_perguntaSobreEmpresa_deveRetornarOrientacaoCorreta() {
        PerguntaRequest request = new PerguntaRequest();
        request.setPergunta("Como abrir uma empresa em Angola?");

        RespostaIA resposta = iaService.responder(request);

        assertThat(resposta.getOrientacaoPratica()).contains("BUE");
    }

    @Test
    void responder_perguntaSobreCrime_deveRetornarOrientacaoCorreta() {
        PerguntaRequest request = new PerguntaRequest();
        request.setPergunta("O que acontece com quem comete um crime?");

        RespostaIA resposta = iaService.responder(request);

        assertThat(resposta.getOrientacaoPratica()).contains("advogado");
    }
}
