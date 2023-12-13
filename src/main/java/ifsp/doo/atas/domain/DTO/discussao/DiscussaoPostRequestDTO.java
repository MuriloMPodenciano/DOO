package ifsp.doo.atas.domain.DTO.discussao;

import ifsp.doo.atas.domain.DTO.pessoa.PessoaGetResponseDTO;

public record DiscussaoPostRequestDTO(String discussao, PessoaGetResponseDTO pessoa) {
    
}
