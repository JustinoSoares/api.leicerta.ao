package ao.leicerta.api.repository;

import ao.leicerta.api.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    List<Artigo> findByLegislacaoId(Long legislacaoId);

    @Query("SELECT a FROM Artigo a WHERE " +
           "LOWER(a.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(a.conteudo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Artigo> buscarPorTermo(@Param("termo") String termo);

    @Query("SELECT a FROM Artigo a WHERE a.legislacao.id = :legislacaoId AND " +
           "(LOWER(a.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(a.conteudo) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<Artigo> buscarPorTermoNaLegislacao(@Param("termo") String termo,
                                            @Param("legislacaoId") Long legislacaoId);
}
