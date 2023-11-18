package ifsp.doo.atas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;

public interface GrupoRepository extends JpaRepository<GrupoGetPersistDTO, Long> {
    public boolean existsByNome(String nome);
}
