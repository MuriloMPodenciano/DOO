package ifsp.doo.atas.domain.DTO.grupo;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Grupo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Grupo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GrupoGetPersistDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Boolean status;

    @OneToMany
    private List<PessoaGetPersistDTO> funcionarios;

    public GrupoGetPersistDTO(Grupo grupo) {
        this(
            grupo.getId(),
            grupo.getNome(),
            grupo.getStatus(),
            grupo.getFuncionarios()
                .stream()
                .map(PessoaGetPersistDTO::new)
                .collect(Collectors.toList())
        );
    }
}
