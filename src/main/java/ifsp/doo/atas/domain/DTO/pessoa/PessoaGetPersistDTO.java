package ifsp.doo.atas.domain.DTO.pessoa;

import ifsp.doo.atas.domain.model.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pessoa")
public record PessoaGetPersistDTO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String nome,
    String email,
    String cargo,
    Boolean status
) {
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
