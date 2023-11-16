package ifsp.doo.atas.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;

public interface AtaDAO extends JpaRepository<AtaGetPersistDTO, Long> {
    @Query("SELECT * FROM ATA ata WHERE ata.dataInicio >= ?1 AND ata.dataInicio <= ?2")
    List<AtaGetPersistDTO> findAllByRange(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT * FROM ATA ata WHERE ata.grupo.id = ?1")
    List<AtaGetPersistDTO> findAllByGrupoId(Long id);

}
