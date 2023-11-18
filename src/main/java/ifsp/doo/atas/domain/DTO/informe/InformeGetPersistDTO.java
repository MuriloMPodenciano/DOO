package ifsp.doo.atas.domain.DTO.informe;

import ifsp.doo.atas.domain.model.Informe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Informe")
public record InformeGetPersistDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String nome,
    String descricao
) {
    public InformeGetPersistDTO(Informe informe) {
        this(informe.getId(), informe.getNome(), informe.getDescricao());
    }
}
