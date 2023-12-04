package ifsp.doo.atas.domain.DTO.pessoa;

import ifsp.doo.atas.domain.model.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PessoaGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cargo;
    private Boolean status;

    public PessoaGetPersistDTO(Pessoa pessoa) {
        this(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getEmail().toString(),
            pessoa.getCargo(),
            pessoa.getStatus()
        );
    }
}
