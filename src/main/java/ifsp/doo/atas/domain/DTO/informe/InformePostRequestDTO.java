package ifsp.doo.atas.domain.DTO.informe;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record InformePostRequestDTO(String informe, PessoaGetResponseDTO pessoa) {
}
