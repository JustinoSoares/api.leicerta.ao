package ao.leicerta.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespostaIA {

    private String pergunta;
    private String resposta;
    private List<FonteLegal> fontes;
    private String orientacaoPratica;
    private String aviso;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FonteLegal {
        private String documento;
        private String tipo;
        private String artigo;
        private String trecho;
    }
}
