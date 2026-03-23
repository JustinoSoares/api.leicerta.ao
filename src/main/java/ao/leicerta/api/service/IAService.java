package ao.leicerta.api.service;

import ao.leicerta.api.model.Artigo;
import ao.leicerta.api.model.BuscaRequest;
import ao.leicerta.api.model.BuscaResponse;
import ao.leicerta.api.model.PerguntaRequest;
import ao.leicerta.api.model.RespostaIA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IAService {

    private static final String AVISO_LEGAL =
            "As informações fornecidas têm caráter informativo e educativo, " +
            "não substituindo a consulta a um advogado ou especialista jurídico.";

    private final BuscaService buscaService;

    public RespostaIA responder(PerguntaRequest request) {
        BuscaRequest buscaRequest = new BuscaRequest();
        buscaRequest.setConsulta(request.getPergunta());
        buscaRequest.setLimite(5);
        BuscaResponse buscaResponse = buscaService.buscar(buscaRequest);

        List<RespostaIA.FonteLegal> fontes = buscaResponse.getResultados().stream()
                .map(r -> RespostaIA.FonteLegal.builder()
                        .documento(r.getLegislacaoTitulo())
                        .tipo(r.getTipo() != null ? r.getTipo().name() : "")
                        .artigo("Artigo " + r.getArtigoNumero() + "º — " + r.getArtigoTitulo())
                        .trecho(r.getTrecho())
                        .build())
                .collect(Collectors.toList());

        String resposta = gerarResposta(request.getPergunta(), buscaResponse.getResultados());
        String orientacao = gerarOrientacao(request.getPergunta());

        return RespostaIA.builder()
                .pergunta(request.getPergunta())
                .resposta(resposta)
                .fontes(fontes)
                .orientacaoPratica(orientacao)
                .aviso(AVISO_LEGAL)
                .build();
    }

    private String gerarResposta(String pergunta, List<BuscaResponse.ResultadoBusca> resultados) {
        if (resultados.isEmpty()) {
            return "Não foram encontradas referências legais específicas para a sua questão na base de dados atual. " +
                   "Recomenda-se consultar um advogado ou o Ministério da Justiça e dos Direitos Humanos de Angola.";
        }
        BuscaResponse.ResultadoBusca principal = resultados.get(0);
        return String.format(
                "Com base na legislação angolana, especificamente no %s, " +
                "Artigo %dº (%s), encontramos informação relevante para a sua questão: %s",
                principal.getLegislacaoTitulo(),
                principal.getArtigoNumero(),
                principal.getArtigoTitulo(),
                principal.getTrecho()
        );
    }

    private String gerarOrientacao(String pergunta) {
        String lower = pergunta.toLowerCase();
        if (lower.contains("trabalho") || lower.contains("emprego") || lower.contains("salário")) {
            return "Dirija-se ao Ministério do Trabalho ou ao Instituto Nacional do Emprego e Formação Profissional (INEFOP) para mais informações e assistência.";
        } else if (lower.contains("empresa") || lower.contains("negócio") || lower.contains("comercial")) {
            return "Consulte o Balcão Único do Empreendedor (BUE) ou o INAPEM para orientação sobre constituição e registo de empresas em Angola.";
        } else if (lower.contains("crime") || lower.contains("penal") || lower.contains("prisão")) {
            return "Em casos de natureza penal, contacte imediatamente um advogado ou a Ordem dos Advogados de Angola. Em emergência, contacte a Polícia Nacional de Angola.";
        } else if (lower.contains("terra") || lower.contains("propriedade") || lower.contains("imóvel")) {
            return "Para questões relacionadas com terras e propriedades, consulte o Ministério do Ordenamento do Território e Habitação ou os Cartórios Notariais.";
        } else if (lower.contains("família") || lower.contains("casamento") || lower.contains("divórcio") || lower.contains("herança")) {
            return "Para questões de direito da família, recorra ao Tribunal Provincial ou a um advogado especializado em direito da família.";
        }
        return "Para mais informações e assistência jurídica, consulte a Ordem dos Advogados de Angola " +
               "ou o Ministério da Justiça e dos Direitos Humanos.";
    }
}
