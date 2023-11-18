package ifsp.doo.atas.domain.DTO.grupo;

import java.util.List;

import ifsp.doo.atas.domain.model.Pessoa;

public record GrupoPostRequestDTO(String nome, List<Pessoa> funcionarios) {
}
