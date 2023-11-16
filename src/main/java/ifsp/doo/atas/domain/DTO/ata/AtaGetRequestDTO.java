package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.model.AtaBuscarMode;

public record AtaGetRequestDTO(Long id, LocalDateTime dataInicio, LocalDateTime dataFim, Long grupoId, AtaBuscarMode modo) {
    
}
