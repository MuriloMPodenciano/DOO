package ifsp.doo.atas.domain.DTO.informe;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record InformePutRequestDTO(Long id, String informe, PessoaGetResponseDTO pessoa) {
    
}
