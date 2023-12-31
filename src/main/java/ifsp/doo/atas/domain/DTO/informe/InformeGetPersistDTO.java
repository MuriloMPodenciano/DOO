package ifsp.doo.atas.domain.DTO.informe;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Informe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Informe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InformeGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String informe;

    @ManyToOne
    private PessoaGetPersistDTO pessoa;

    private Boolean informado;

    public InformeGetPersistDTO(Informe informe) {
        this(informe.getId(), informe.getInforme(), new PessoaGetPersistDTO(informe.getPessoa()), informe.getInformado());
    }
}
