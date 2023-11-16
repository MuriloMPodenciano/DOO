package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.informe.InformesPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaPostRequestDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaPostRequestDTO;

public record AtaPostRequestDTO(
    Long id,
    String titulo,
    String discussao,
    String textoAbertura,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,
    GrupoPostRequestDTO grupo,
    EncerramentoPostRequestDTO encerramento,
    List<PessoaPostRequestDTO> listaPresenca,
    List<PautaPostRequestDTO> pautas,
    List<InformesPostRequestDTO> informes
) {
}
