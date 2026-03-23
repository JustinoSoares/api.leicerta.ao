package ao.leicerta.api.controller;

import ao.leicerta.api.model.PerguntaRequest;
import ao.leicerta.api.model.RespostaIA;
import ao.leicerta.api.service.IAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ia")
@RequiredArgsConstructor
@Tag(name = "IA Jurídica", description = "Interpretação de linguagem natural com referência à lei angolana")
public class IAController {

    private final IAService iaService;

    @PostMapping("/perguntar")
    @Operation(summary = "Faz uma pergunta jurídica em linguagem natural",
               description = "A IA interpreta a pergunta, busca na legislação e retorna uma resposta fundamentada com referências legais e orientação prática")
    public ResponseEntity<RespostaIA> perguntar(@Valid @RequestBody PerguntaRequest request) {
        return ResponseEntity.ok(iaService.responder(request));
    }
}
