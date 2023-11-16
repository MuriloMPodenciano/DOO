package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformesGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Ata;

public record AtaGetPersistDTO(
    Long id,
    String titulo,
    String discussao,
    String textoAbertura,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,
    GrupoGetPersistDTO grupo,
    EncerramentoGetPersistDTO encerramento,
    List<PessoaGetPersistDTO> listaPresenca,
    List<PautaGetPersistDTO> pautas,
    List<InformesGetPersistDTO> informes
) {
    public AtaGetPersistDTO(Ata ata) {
        this(
            ata.getId(),
            ata.getTitulo(),
            ata.getDiscussao(),
            ata.getTextoAbertura(),
            ata.getDataInicio(),
            ata.getFimPrevisto(),
            ata.getLocal(),
            ata.getGrupo(),
            ata.getEncerramento(),
            ata.getListaPresenca(),
            ata.getPautas(),
            ata.getInformes()
        );
    }

}
