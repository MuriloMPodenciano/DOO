package ifsp.doo.atas.domain.DTO.pauta;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.discussao.DiscussaoGetPersistDTO;
import ifsp.doo.atas.domain.model.Pauta;
import ifsp.doo.atas.domain.model.PautaState;
import ifsp.doo.atas.domain.model.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pauta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PautaGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pauta;

    @ManyToOne
    private Pessoa pessoa;

    @OneToMany
    @OrderColumn
    private List<DiscussaoGetPersistDTO> discussoes;

    private String decisao;

    private PautaState estado;

    public PautaGetPersistDTO(Pauta pauta) {
        this(
            pauta.getId(),
            pauta.getPauta(),
            pauta.getPessoa(),
            pauta.getDiscussoes()
                .stream()
                .map(DiscussaoGetPersistDTO::new)
                .collect(Collectors.toList()),
            pauta.getDecisao(),
            pauta.getEstado()
        );
    }
}
