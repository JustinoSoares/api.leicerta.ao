package ao.leicerta.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "legislacao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Legislacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipo;

    private String numero;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Column(name = "diario_republica")
    private String diarioRepublica;

    @Column(name = "url_documento")
    private String urlDocumento;

    @Column(columnDefinition = "TEXT")
    private String palavrasChave;

    @OneToMany(mappedBy = "legislacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Artigo> artigos = new ArrayList<>();
}
