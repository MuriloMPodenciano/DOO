package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.model.AtaBuscarModo;

public record AtaGetRequestDTO(
    String palavraChave,

    LocalDateTime dataInicio,
    LocalDateTime dataFim,

    Long grupoId,

    AtaBuscarModo modo
) {
}
