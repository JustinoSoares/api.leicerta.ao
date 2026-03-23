package ao.leicerta.api.service;

import ao.leicerta.api.exception.ResourceNotFoundException;
import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.repository.LegislacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final LegislacaoRepository legislacaoRepository;

    public Resource obterDocumento(Long legislacaoId) {
        Legislacao legislacao = legislacaoRepository.findById(legislacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Legislação", legislacaoId));

        if (legislacao.getUrlDocumento() == null || legislacao.getUrlDocumento().isBlank()) {
            throw new ResourceNotFoundException("Documento para legislação", legislacaoId);
        }

        try {
            Resource resource = new UrlResource(URI.create(legislacao.getUrlDocumento()).toURL());
            if (resource.exists()) {
                return resource;
            }
            throw new ResourceNotFoundException("Documento para legislação", legislacaoId);
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("URL do documento inválida para legislação " + legislacaoId);
        }
    }

    public String obterUrlDocumento(Long legislacaoId) {
        Legislacao legislacao = legislacaoRepository.findById(legislacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Legislação", legislacaoId));

        if (legislacao.getUrlDocumento() == null || legislacao.getUrlDocumento().isBlank()) {
            throw new ResourceNotFoundException("Documento para legislação", legislacaoId);
        }

        return legislacao.getUrlDocumento();
    }
}
