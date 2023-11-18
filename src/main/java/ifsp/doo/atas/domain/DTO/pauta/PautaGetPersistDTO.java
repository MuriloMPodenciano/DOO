package ifsp.doo.atas.domain.DTO.pauta;

import ifsp.doo.atas.domain.model.Pauta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pauta")
public record PautaGetPersistDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String nome,
    String descricao
) {
    public PautaGetPersistDTO(Pauta pauta) {
        this(pauta.getId(), pauta.getNome(), pauta.getDescricao());
    }
}
