package ao.leicerta.api.controller;

import ao.leicerta.api.model.BuscaRequest;
import ao.leicerta.api.model.BuscaResponse;
import ao.leicerta.api.service.BuscaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/busca")
@RequiredArgsConstructor
@Tag(name = "Busca Inteligente", description = "Pesquisa semântica na legislação angolana (RAG)")
public class BuscaController {

    private final BuscaService buscaService;

    @PostMapping
    @Operation(summary = "Realiza busca inteligente na legislação",
               description = "Pesquisa por termos nos títulos, descrições e conteúdos dos artigos")
    public ResponseEntity<BuscaResponse> buscar(@Valid @RequestBody BuscaRequest request) {
        return ResponseEntity.ok(buscaService.buscar(request));
    }

    @GetMapping
    @Operation(summary = "Busca rápida por termo (GET)",
               description = "Versão simplificada de busca via parâmetro de URL")
    public ResponseEntity<BuscaResponse> buscarGet(@RequestParam String q) {
        BuscaRequest request = new BuscaRequest();
        request.setConsulta(q);
        return ResponseEntity.ok(buscaService.buscar(request));
    }
}
