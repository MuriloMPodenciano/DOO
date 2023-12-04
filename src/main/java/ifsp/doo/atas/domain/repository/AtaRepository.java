package ifsp.doo.atas.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;

public interface AtaRepository extends JpaRepository<AtaGetPersistDTO, Long> {
    @Query("SELECT * FROM Ata ata WHERE ata.titulo LIKE '%?1%'")
    List<AtaGetPersistDTO> findAllByPalavraChave(String palavraChave);

    @Query("SELECT * FROM Ata ata WHERE ata.dataInicio >= ?1 AND ata.dataInicio <= ?2")
    List<AtaGetPersistDTO> findAllByRange(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT * FROM Ata ata WHERE ata.grupo.id = ?1")
    List<AtaGetPersistDTO> findAllByGrupoId(Long id);
}
