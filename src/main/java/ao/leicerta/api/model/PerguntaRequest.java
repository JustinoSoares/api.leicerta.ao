package ao.leicerta.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PerguntaRequest {

    @NotBlank(message = "A pergunta não pode estar vazia")
    private String pergunta;

    private String contexto;
}
