package ao.leicerta.api.repository;

import ao.leicerta.api.model.Legislacao;
import ao.leicerta.api.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegislacaoRepository extends JpaRepository<Legislacao, Long> {

    List<Legislacao> findByTipo(TipoDocumento tipo);

    List<Legislacao> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT l FROM Legislacao l WHERE " +
           "LOWER(l.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.palavrasChave) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Legislacao> buscarPorTermo(@Param("termo") String termo);

    @Query("SELECT l FROM Legislacao l WHERE l.tipo = :tipo AND " +
           "(LOWER(l.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.descricao) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<Legislacao> buscarPorTermoETipo(@Param("termo") String termo, @Param("tipo") TipoDocumento tipo);
}
