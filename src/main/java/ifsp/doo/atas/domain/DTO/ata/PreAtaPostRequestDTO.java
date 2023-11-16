package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;

public record PreAtaPostRequestDTO(String titulo, LocalDateTime dataInicio, String local, GrupoPostRequestDTO grupo) {
    
}
