package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.encerramento.EncerramentoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Ata;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ata")
public record AtaGetPersistDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String titulo,
    String discussao,
    String textoAbertura,
    LocalDateTime dataInicio,
    LocalDateTime fimPrevisto,
    String local,

    @ManyToOne
    GrupoGetPersistDTO grupo,

    @Embedded
    @OneToOne
    EncerramentoGetPersistDTO encerramento,

    @OneToMany
    List<PessoaGetPersistDTO> listaPresenca,

    @OneToMany
    List<PautaGetPersistDTO> pautas,

    @OneToMany
    List<InformeGetPersistDTO> informes
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
            new GrupoGetPersistDTO(ata.getGrupo()),
            new EncerramentoGetPersistDTO(ata.getEncerramento()),
            ata.getListaPresenca()
                .stream()
                .map(PessoaGetPersistDTO::new)
                .collect(Collectors.toList()),
            ata.getPautas()
                .stream()
                .map(PautaGetPersistDTO::new)
                .collect(Collectors.toList()),
            ata.getInformes()
                .stream()
                .map(InformeGetPersistDTO::new)
                .collect(Collectors.toList())
        );
    }

}
