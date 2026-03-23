package ao.leicerta.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BuscaRequest {

    @NotBlank(message = "A consulta não pode estar vazia")
    private String consulta;

    private TipoDocumento tipo;

    private Integer limite;
}
