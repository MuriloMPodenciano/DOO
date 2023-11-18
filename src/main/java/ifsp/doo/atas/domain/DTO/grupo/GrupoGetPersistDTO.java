package ifsp.doo.atas.domain.DTO.grupo;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO;
import ifsp.doo.atas.domain.model.Grupo;

public record GrupoGetPersistDTO(Long id, String nome, boolean status, List<PessoaGetPersistDTO> funcionarios) {
    public GrupoGetPersistDTO(Grupo grupo) {
        this(
            grupo.getId(),
            grupo.getNome(),
            grupo.isStatus(),
            grupo.getFuncionarios()
                .stream()
                .map(PessoaGetPersistDTO::new)
                .collect(Collectors.toList())
        );
    }
}
