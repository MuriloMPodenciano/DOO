package ifsp.doo.atas.domain.DTO.discussao;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Discussao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Discussao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DiscussaoGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String discussao;
    
    @OneToOne
    private PessoaGetPersistDTO pessoa;

    public DiscussaoGetPersistDTO(Discussao discussao) {
        this(discussao.getId(), discussao.getDiscussao(), new PessoaGetPersistDTO(discussao.getPessoa()));
    }
}
