package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;

public record PreAtaPostRequestDTO(
    String titulo,
    LocalDateTime dataInicio,
    String local,
    GrupoGetResponseDTO grupo
) {
}
