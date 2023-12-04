package ifsp.doo.atas.domain.DTO.ata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.informe.InformeGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pauta.PautaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Ata;
import ifsp.doo.atas.domain.model.AtaState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ata")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AtaGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String textoAbertura;

    @OneToMany
    private PessoaGetPersistDTO pessoa;

    private LocalDateTime dataInicio;
    private LocalDateTime fimPrevisto;
    private String local;

    @ManyToOne
    private GrupoGetPersistDTO grupo;

    private String textoEncerramento;

    private LocalDateTime horaEncerramento;

    private String nomeRedator;

    @ManyToMany
    @OrderColumn
    private Set<PessoaGetPersistDTO> listaPresenca;

    @OneToMany
    @OrderColumn
    private List<PautaGetPersistDTO> pautas;

    @OneToMany
    @OrderColumn
    private List<InformeGetPersistDTO> informes;

    private AtaState estado;

    public AtaGetPersistDTO(Ata ata) {
        this(
            ata.getId(),
            ata.getTitulo(),
            ata.getDescricao(),
            ata.getTextoAbertura(),
            new PessoaGetPersistDTO(ata.getPessoa()),
            ata.getDataInicio(),
            ata.getFimPrevisto(),
            ata.getLocal(),
            new GrupoGetPersistDTO(ata.getGrupo()),
            ata.getTextoEncerramento(),
            ata.getHoraEncerramento(),
            ata.getNomeRedator(),
            ata.getListaPresenca()
                .stream()
                .map(PessoaGetPersistDTO::new)
                .collect(Collectors.toSet()),
            ata.getPautas()
                .stream()
                .map(PautaGetPersistDTO::new)
                .collect(Collectors.toList()),
            ata.getInformes()
                .stream()
                .map(InformeGetPersistDTO::new)
                .collect(Collectors.toList()),
            ata.getEstado()
        );
    }
}
