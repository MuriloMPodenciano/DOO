package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoPutRequestDTO;;

public record AtaPutRequestDTO(Long id, LocalDateTime dataInicio, String local, String discussao, String textoAbertura, EncerramentoPutRequestDTO encerramento) {
    
}
