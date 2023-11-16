package ifsp.doo.atas.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    boolean existByNome(String nome);
}
