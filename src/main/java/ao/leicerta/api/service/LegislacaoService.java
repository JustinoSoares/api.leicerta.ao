package ao.leicerta.api.service;

import ao.leicerta.api.exception.ResourceNotFoundException;
import ao.leicerta.api.model.Artigo;
import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.model.TipoDocumento;
import ao.leicerta.api.repository.ArtigoRepository;
import ao.leicerta.api.repository.LegislacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LegislacaoService {

    private final LegislacaoRepository legislacaoRepository;
    private final ArtigoRepository artigoRepository;

    public List<Legislacao> listarTodas() {
        return legislacaoRepository.findAll();
    }

    public List<Legislacao> listarPorTipo(TipoDocumento tipo) {
        return legislacaoRepository.findByTipo(tipo);
    }

    public Legislacao buscarPorId(Long id) {
        return legislacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Legislação", id));
    }

    public List<Artigo> listarArtigosDaLegislacao(Long legislacaoId) {
        buscarPorId(legislacaoId);
        return artigoRepository.findByLegislacaoId(legislacaoId);
    }

    public Artigo buscarArtigo(Long legislacaoId, Long artigoId) {
        buscarPorId(legislacaoId);
        return artigoRepository.findById(artigoId)
                .filter(a -> a.getLegislacao().getId().equals(legislacaoId))
                .orElseThrow(() -> new ResourceNotFoundException("Artigo", artigoId));
    }

    @Transactional
    public Legislacao salvar(Legislacao legislacao) {
        return legislacaoRepository.save(legislacao);
    }

    @Transactional
    public void deletar(Long id) {
        buscarPorId(id);
        legislacaoRepository.deleteById(id);
    }
}
