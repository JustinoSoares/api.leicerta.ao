package ao.leicerta.api.service;

import ao.leicerta.api.model.Artigo;
import ao.leicerta.api.model.BuscaRequest;
import ao.leicerta.api.model.BuscaResponse;
import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.repository.ArtigoRepository;
import ao.leicerta.api.repository.LegislacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuscaService {

    private final LegislacaoRepository legislacaoRepository;
    private final ArtigoRepository artigoRepository;

    public BuscaResponse buscar(BuscaRequest request) {
        String consulta = request.getConsulta().trim();
        int limite = request.getLimite() != null ? request.getLimite() : 10;

        List<Artigo> artigos;
        if (request.getTipo() != null) {
            List<Legislacao> legislacoes = legislacaoRepository.buscarPorTermoETipo(consulta, request.getTipo());
            List<Long> ids = legislacoes.stream().map(Legislacao::getId).collect(Collectors.toList());
            artigos = artigoRepository.buscarPorTermo(consulta)
                    .stream()
                    .filter(a -> ids.contains(a.getLegislacao().getId()))
                    .collect(Collectors.toList());
        } else {
            artigos = artigoRepository.buscarPorTermo(consulta);
        }

        List<BuscaResponse.ResultadoBusca> resultados = new ArrayList<>();
        for (Artigo artigo : artigos) {
            String trecho = extrairTrecho(artigo.getConteudo(), consulta);
            double relevancia = calcularRelevancia(artigo, consulta);
            resultados.add(BuscaResponse.ResultadoBusca.builder()
                    .legislacaoId(artigo.getLegislacao().getId())
                    .legislacaoTitulo(artigo.getLegislacao().getTitulo())
                    .tipo(artigo.getLegislacao().getTipo())
                    .artigoNumero(artigo.getNumero())
                    .artigoTitulo(artigo.getTitulo())
                    .trecho(trecho)
                    .relevancia(relevancia)
                    .build());
        }

        resultados.sort((a, b) -> Double.compare(b.getRelevancia(), a.getRelevancia()));
        List<BuscaResponse.ResultadoBusca> limitados = resultados.stream().limit(limite).collect(Collectors.toList());

        return BuscaResponse.builder()
                .consulta(consulta)
                .totalResultados(limitados.size())
                .resultados(limitados)
                .build();
    }

    private String extrairTrecho(String conteudo, String termo) {
        if (conteudo == null) return "";
        int index = conteudo.toLowerCase().indexOf(termo.toLowerCase());
        if (index == -1) return conteudo.substring(0, Math.min(200, conteudo.length())) + "...";
        int inicio = Math.max(0, index - 80);
        int fim = Math.min(conteudo.length(), index + 120);
        String trecho = conteudo.substring(inicio, fim);
        if (inicio > 0) trecho = "..." + trecho;
        if (fim < conteudo.length()) trecho = trecho + "...";
        return trecho;
    }

    private double calcularRelevancia(Artigo artigo, String consulta) {
        String termoLower = consulta.toLowerCase();
        double score = 0.0;
        String tituloLower = artigo.getTitulo().toLowerCase();
        String conteudoLower = artigo.getConteudo().toLowerCase();
        if (tituloLower.contains(termoLower)) score += 0.5;
        long ocorrencias = conteudoLower.split(termoLower, -1).length - 1;
        score += Math.min(ocorrencias * 0.1, 0.5);
        return Math.min(score, 1.0);
    }
}
