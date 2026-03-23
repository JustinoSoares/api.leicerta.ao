package ao.leicerta.api.controller;

import ao.leicerta.api.model.Artigo;
import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.model.TipoDocumento;
import ao.leicerta.api.service.LegislacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/legislacao")
@RequiredArgsConstructor
@Tag(name = "Legislação", description = "Consulta de legislação angolana")
public class LegislacaoController {

    private final LegislacaoService legislacaoService;

    @GetMapping
    @Operation(summary = "Lista toda a legislação disponível")
    public ResponseEntity<List<Legislacao>> listarTodas(
            @RequestParam(required = false) TipoDocumento tipo) {
        if (tipo != null) {
            return ResponseEntity.ok(legislacaoService.listarPorTipo(tipo));
        }
        return ResponseEntity.ok(legislacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma legislação pelo ID")
    public ResponseEntity<Legislacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(legislacaoService.buscarPorId(id));
    }

    @GetMapping("/{id}/artigos")
    @Operation(summary = "Lista os artigos de uma legislação")
    public ResponseEntity<List<Artigo>> listarArtigos(@PathVariable Long id) {
        return ResponseEntity.ok(legislacaoService.listarArtigosDaLegislacao(id));
    }

    @GetMapping("/{id}/artigos/{artigoId}")
    @Operation(summary = "Obtém um artigo específico de uma legislação")
    public ResponseEntity<Artigo> buscarArtigo(
            @PathVariable Long id,
            @PathVariable Long artigoId) {
        return ResponseEntity.ok(legislacaoService.buscarArtigo(id, artigoId));
    }

    @PostMapping
    @Operation(summary = "Adiciona nova legislação")
    public ResponseEntity<Legislacao> criar(@Valid @RequestBody Legislacao legislacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(legislacaoService.salvar(legislacao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma legislação")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        legislacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
