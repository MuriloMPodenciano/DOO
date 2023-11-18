package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record AtaGetResponseDTO(
    Long id,
    String titulo,
    String discussao,
    String textoAbertura,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,
    EncerramentoGetResponseDTO encerramento,
    List<PessoaGetResponseDTO> listaPresenca,
    List<PautaGetResponseDTO> pautas,
    List<InformeGetResponseDTO> informes
) {
    public AtaGetResponseDTO(AtaGetPersistDTO ata) {
        this(
            ata.id(),
            ata.titulo(),
            ata.discussao(),
            ata.textoAbertura(),
            ata.dataInicio(),
            ata.fimPrevisto(),
            ata.local(),
            new EncerramentoGetResponseDTO(ata.encerramento()),
            ata.listaPresenca()
                .stream()
                .map(PessoaGetResponseDTO::new)
                .collect(Collectors.toList()),
            ata.pautas()
                .stream()
                .map(PautaGetResponseDTO::new)
                .collect(Collectors.toList()),
            ata.informes()
                .stream()
                .map(InformeGetResponseDTO::new)
                .collect(Collectors.toList())
        );
    }
}
