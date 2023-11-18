package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoPostRequestDTO;

public record AtaPutRequestDTO(
    Long id,
    LocalDateTime dataInicio,
    String local,
    String discussao,
    String textoAbertura,
    EncerramentoPostRequestDTO encerramento
) {
}
