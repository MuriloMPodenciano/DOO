package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record AtaPutRequestDTO(
    Long id,
    String titulo,
    String descricao,
    String textoAbertura,
    PessoaGetResponseDTO pessoa,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,
    GrupoGetResponseDTO grupo,
    String textoEncerramento,
    LocalDateTime horaEncerramento
) {
}
