package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetResponseDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;
import ifsp.doo.atas.domain.model.AtaState;

public record AtaGetResponseDTO(
    Long id,
    String titulo,
    String descricao,
    String textoAbertura,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,
    GrupoGetResponseDTO grupo,
    String textoEncerramento,
    LocalDateTime horaEncerramento,
    String nomeRedator,
    Set<PessoaGetResponseDTO> listaPresenca,
    List<PautaGetResponseDTO> pautas,
    List<InformeGetResponseDTO> informes,
    AtaState estado
) {
    public AtaGetResponseDTO(AtaGetPersistDTO ata) {
        this(
            ata.getId(),
            ata.getTitulo(),
            ata.getDescricao(),
            ata.getTextoAbertura(),
            ata.getDataInicio(),
            ata.getFimPrevisto(),
            ata.getLocal(),
            new GrupoGetResponseDTO(ata.getGrupo()),
            ata.getTextoEncerramento(),
            ata.getHoraEncerramento(),
            ata.getNomeRedator(),
            ata.getListaPresenca()
                .stream()
                .map(PessoaGetResponseDTO::new)
                .collect(Collectors.toSet()),
            ata.getPautas()
                .stream()
                .map(PautaGetResponseDTO::new)
                .collect(Collectors.toList()),
            ata.getInformes()
                .stream()
                .map(InformeGetResponseDTO::new)
                .collect(Collectors.toList()),
            ata.getEstado()
        );
    }
}
