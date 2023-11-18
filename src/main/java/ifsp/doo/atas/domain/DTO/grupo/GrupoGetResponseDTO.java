package ifsp.doo.atas.domain.DTO.grupo;

import java.util.List;
import java.util.stream.Collectors;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;


public record GrupoGetResponseDTO(Long id, String nome, boolean status, List<PessoaGetResponseDTO> funcionarios) {
    public GrupoGetResponseDTO(GrupoGetPersistDTO grupo) {
        this(
            grupo.id(),
            grupo.nome(),
            grupo.status(),
            grupo.funcionarios()
                .stream()
                .map(PessoaGetResponseDTO::new)
                .collect(Collectors.toList())
        );
    }
}
