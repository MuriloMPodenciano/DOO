package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformePostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;

public record PreAtaPostRequestDTO(
    String titulo,
    String descricao,
    LocalDateTime dataInicio,
    String local,
    GrupoGetResponseDTO grupo,
    List<PautaPostRequestDTO> pautas,
    List<InformePostRequestDTO> informes
) {
}
