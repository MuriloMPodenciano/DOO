package ifsp.doo.atas.domain.DTO.grupo;

import java.util.List;

public record GrupoPostRequestDTO(String nome, List<ifsp.doo.atas.domain.DTO.pessoa.PessoaGetPersistDTO> funcionarios) {
}
