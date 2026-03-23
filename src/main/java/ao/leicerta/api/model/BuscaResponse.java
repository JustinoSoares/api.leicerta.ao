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
public class BuscaResponse {

    private String consulta;
    private int totalResultados;
    private List<ResultadoBusca> resultados;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultadoBusca {
        private Long legislacaoId;
        private String legislacaoTitulo;
        private TipoDocumento tipo;
        private Integer artigoNumero;
        private String artigoTitulo;
        private String trecho;
        private double relevancia;
    }
}
