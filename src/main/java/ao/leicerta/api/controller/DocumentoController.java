package ao.leicerta.api.controller;

import ao.leicerta.api.service.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/documentos")
@RequiredArgsConstructor
@Tag(name = "Documentos Oficiais", description = "Download e acesso a documentos legais oficiais")
public class DocumentoController {

    private final DocumentoService documentoService;

    @GetMapping("/{legislacaoId}/url")
    @Operation(summary = "Obtém a URL do documento oficial",
               description = "Retorna o link directo para o documento oficial (PDF) da legislação")
    public ResponseEntity<Map<String, String>> obterUrl(@PathVariable Long legislacaoId) {
        String url = documentoService.obterUrlDocumento(legislacaoId);
        return ResponseEntity.ok(Map.of("url", url, "legislacaoId", String.valueOf(legislacaoId)));
    }
}
