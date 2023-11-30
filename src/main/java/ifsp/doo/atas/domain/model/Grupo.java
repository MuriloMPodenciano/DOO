package ifsp.doo.atas.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.grupo.GrupoGetPersistDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoGetResponseDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPostRequestDTO;
import ifsp.doo.atas.domain.DTO.grupo.GrupoPutRequestDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Grupo {
    private Long id;
    private String nome;
    private boolean status;
    private List<Pessoa> funcionarios;

    public Grupo(String nome, List<Pessoa> funcionarios) {
        this(null, nome, true, funcionarios);
    }

    public Grupo(GrupoPostRequestDTO grupoDTO) {
        this(grupoDTO.nome(), grupoDTO.funcionarios());
    }

    public Grupo(GrupoGetPersistDTO grupoBanco) {
        this(
            grupoBanco.id(),
            grupoBanco.nome(),
            grupoBanco.status(),
            grupoBanco.funcionarios()
                .stream()
                .map(Pessoa::new)
                .collect(Collectors.toList())
        );
    }

    public Grupo(GrupoGetResponseDTO grupo) {
        this(
            grupo.id(),
            grupo.nome(),
            grupo.status(),
            grupo.funcionarios()
                .stream()
                .map(Pessoa::new)
                .collect(Collectors.toList())
        );
    }

    public void inserirFuncionario(Pessoa pessoa) {
        funcionarios.add(pessoa);
    }

    public void atualizarGrupo(GrupoPutRequestDTO grupoDTO) {
        status = grupoDTO.status();
    }

    public boolean temFuncionario(Pessoa pessoa) {
        return funcionarios.contains(pessoa);
    }
}
